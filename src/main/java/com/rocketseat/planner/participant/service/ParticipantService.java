package com.rocketseat.planner.participant.service;

import com.rocketseat.planner.participant.domain.Participant;
import com.rocketseat.planner.participant.request.ParticipantRequestPayload;
import com.rocketseat.planner.participant.response.ParticipantCreateResponse;
import com.rocketseat.planner.participant.response.ParticipantDataResponse;
import com.rocketseat.planner.trip.domain.Trip;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    void registerParticipantsToEvent(List<String> partipantsToInvite, Trip trip);

    void triggerConfirmationEmailToParticipants(UUID tripId);

    Participant confirmParticipant(UUID participantId, ParticipantRequestPayload requestPayload);

    void triggerConfirmationEmailToParticipant(String email);

    ParticipantCreateResponse inviteParticipantToTrip(String email, Trip trip);

    ParticipantCreateResponse inviteParticipantToTrip(UUID id, ParticipantRequestPayload participantRequestPayload);

    List<ParticipantDataResponse> getAllParticipantsFromTrip(UUID tripId);

}
