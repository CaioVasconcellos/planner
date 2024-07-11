package com.rocketseat.planner.activity.service;

import com.rocketseat.planner.activity.request.ActivityRequestPayload;
import com.rocketseat.planner.activity.response.ActivityDataResponse;
import com.rocketseat.planner.activity.response.ActivityResponse;
import com.rocketseat.planner.trip.domain.Trip;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    ActivityResponse createActivity(ActivityRequestPayload payload, Trip trip);

    ActivityResponse registerActivity(UUID id, ActivityRequestPayload payload);



    List<ActivityDataResponse> getAllActivitiesFromTrip(UUID id);
}
