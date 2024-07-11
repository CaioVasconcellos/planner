package com.rocketseat.planner.trip.service;

import com.rocketseat.planner.participant.request.ParticipantRequestPayload;
import com.rocketseat.planner.participant.response.ParticipantCreateResponse;
import com.rocketseat.planner.trip.domain.Trip;
import com.rocketseat.planner.trip.request.TripRequestPayload;

import java.util.UUID;

public interface TripService {

    Trip create (TripRequestPayload tripRequestPayload);

    Trip findById(UUID tripId);

    Trip updateTrip(UUID tripId, TripRequestPayload tripRequestPayload);

    Trip getConfirmationTrip(UUID tripId);

}
