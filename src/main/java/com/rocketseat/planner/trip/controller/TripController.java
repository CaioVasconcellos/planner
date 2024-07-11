package com.rocketseat.planner.trip.controller;


import com.rocketseat.planner.activity.request.ActivityRequestPayload;
import com.rocketseat.planner.activity.response.ActivityDataResponse;
import com.rocketseat.planner.activity.response.ActivityResponse;
import com.rocketseat.planner.activity.service.ActivityService;
import com.rocketseat.planner.link.request.LinkRequestPayload;
import com.rocketseat.planner.link.response.LinkDataResponse;
import com.rocketseat.planner.link.response.LinkResponse;
import com.rocketseat.planner.link.service.LinkService;
import com.rocketseat.planner.participant.request.ParticipantRequestPayload;
import com.rocketseat.planner.participant.response.ParticipantCreateResponse;
import com.rocketseat.planner.participant.response.ParticipantDataResponse;
import com.rocketseat.planner.participant.service.ParticipantService;
import com.rocketseat.planner.trip.domain.Trip;
import com.rocketseat.planner.trip.request.TripRequestPayload;
import com.rocketseat.planner.trip.response.TripCreateResponse;
import com.rocketseat.planner.trip.service.TripService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
@Log4j2
public class TripController {

    private ParticipantService participantService;
    private TripService tripService;
    private ActivityService activityService;
    private LinkService linkService;

    //TRIP
    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload payload) {
        Trip newTrip = tripService.create(payload);
        participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip);

        return ResponseEntity.ok(new TripCreateResponse(newTrip.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable UUID id) {
        try {
            Trip tripDetails = tripService.findById(id);
            return ResponseEntity.ok(tripDetails);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTripById(@PathVariable UUID id, @RequestBody TripRequestPayload payload) {
        try {
            Trip updateTrip = tripService.updateTrip(id, payload);
            return ResponseEntity.ok(updateTrip);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        try {
            Trip rawTrip = tripService.getConfirmationTrip(id);
            participantService.triggerConfirmationEmailToParticipants(id);
            return ResponseEntity.ok(rawTrip);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    //PARTICIPANTS
    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipantToTrip(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        try {
            ParticipantCreateResponse participantCreateResponse = participantService.inviteParticipantToTrip(id, payload);
            return ResponseEntity.ok(participantCreateResponse);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @GetMapping("{id}/participants")
    public ResponseEntity<List<ParticipantDataResponse>> getAllParticipants(@PathVariable UUID id) {
        try {
            List<ParticipantDataResponse> allParticipantsFromTrip = participantService.getAllParticipantsFromTrip(id);
            return ResponseEntity.ok(allParticipantsFromTrip);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    //ACTIVITIES

    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID id, @RequestBody ActivityRequestPayload payload) {
        try {
            ActivityResponse activityResponse = activityService.registerActivity(id, payload);
            return ResponseEntity.ok(activityResponse);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tripIdActivity}/activities")
    public ResponseEntity<List<ActivityDataResponse>> getAllActivities(@PathVariable UUID tripIdActivity) {
        try {
            List<ActivityDataResponse> activityDataList = activityService.getAllActivitiesFromTrip(tripIdActivity);
            return ResponseEntity.ok(activityDataList);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    //LINK

    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLinks(@PathVariable UUID id, @RequestBody LinkRequestPayload payload) {
        try {
            LinkResponse linkResponse = linkService.registerLink(id, payload);
            return ResponseEntity.ok(linkResponse);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tripIdLink}/links")
    public ResponseEntity<List<LinkDataResponse>> getAllLinks(@PathVariable UUID tripIdLink) {
        try {
            List<LinkDataResponse> linkDataResponses = linkService.getAllActivitiesFromTrip(tripIdLink);
            return ResponseEntity.ok(linkDataResponses);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }


}
