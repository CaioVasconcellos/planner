package com.rocketseat.planner.participant.service.impl;

import com.rocketseat.planner.participant.domain.Participant;
import com.rocketseat.planner.participant.repository.ParticipantRepository;
import com.rocketseat.planner.participant.request.ParticipantRequestPayload;
import com.rocketseat.planner.participant.response.ParticipantCreateResponse;
import com.rocketseat.planner.participant.response.ParticipantDataResponse;
import com.rocketseat.planner.participant.service.ParticipantService;
import com.rocketseat.planner.trip.domain.Trip;
import com.rocketseat.planner.trip.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository participantRepository;
    private TripService tripService;

    public void registerParticipantsToEvent(List<String> partipantsToInvite, Trip trip) {
        List<Participant> participants = partipantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        participantRepository.saveAll(participants);
        System.out.println(participants.getFirst().getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {
    }

    public void triggerConfirmationEmailToParticipant(String email) {
    }

    public Participant findById(UUID participantId) {
        return participantRepository.findById(participantId).orElseThrow(() -> new NoSuchElementException("Id Participant not found"));
    }

    public Participant confirmParticipant(UUID participantId, ParticipantRequestPayload requestPayload) {
        Participant rawParticipant = findById(participantId);
        rawParticipant.setIsConfirmed(true);
        rawParticipant.setName(requestPayload.name());
        return participantRepository.save(rawParticipant);
    }

    public ParticipantCreateResponse inviteParticipantToTrip(String email, Trip trip) {
        Participant newParticipant = new Participant(email, trip);
        participantRepository.save(newParticipant);
        return new ParticipantCreateResponse(newParticipant.getId());
    }

    @Override
    public ParticipantCreateResponse inviteParticipantToTrip(UUID id, ParticipantRequestPayload participantRequestPayload) {
        Trip rawTrip = tripService.findById(id);
        if (rawTrip.getIsConfirmed()) {
            ParticipantCreateResponse participantCreateResponse = inviteParticipantToTrip(participantRequestPayload.email(), rawTrip);
            triggerConfirmationEmailToParticipant(participantRequestPayload.email());
            return participantCreateResponse;
        } else {
            throw new IllegalStateException("Trip not confirmed");
        }
    }

    @Override
    public List<ParticipantDataResponse> getAllParticipantsFromTrip(UUID tripId) {
        List<Participant> allParticipants = participantRepository.findByTripId(tripId);
        return allParticipants.stream().map(participant ->
                new ParticipantDataResponse(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }


}
