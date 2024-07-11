package com.rocketseat.planner.trip.service.impl;

import com.rocketseat.planner.participant.request.ParticipantRequestPayload;
import com.rocketseat.planner.participant.response.ParticipantCreateResponse;
import com.rocketseat.planner.participant.service.ParticipantService;
import com.rocketseat.planner.trip.domain.Trip;
import com.rocketseat.planner.trip.repository.TripRepository;
import com.rocketseat.planner.trip.request.TripRequestPayload;
import com.rocketseat.planner.trip.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Override
    public Trip create(TripRequestPayload payload) {
        Trip newTrip = new Trip(payload);
        return tripRepository.save(newTrip);
    }

    @Override
    public Trip findById(UUID tripId) {
        return tripRepository.findById(tripId).orElseThrow(() -> new NoSuchElementException("Id not found"));
    }

    @Override
    public Trip updateTrip(UUID tripId, TripRequestPayload tripRequestPayload) {
        Trip rawTrip = findById(tripId);
        rawTrip.setEndsAt(LocalDateTime.parse(tripRequestPayload.ends_at(), DateTimeFormatter.ISO_DATE_TIME));
        rawTrip.setStartsAt(LocalDateTime.parse(tripRequestPayload.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
        rawTrip.setDestination(tripRequestPayload.destination());
        return tripRepository.save(rawTrip);
    }

    @Override
    public Trip getConfirmationTrip(UUID tripId) {
        Trip rawTrip = findById(tripId);
            rawTrip.setIsConfirmed(true);
            return tripRepository.save(rawTrip);

    }


}

