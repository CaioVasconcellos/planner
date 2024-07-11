package com.rocketseat.planner.activity.service.impl;

import com.rocketseat.planner.activity.domain.Activity;
import com.rocketseat.planner.activity.repository.ActivityRepository;
import com.rocketseat.planner.activity.request.ActivityRequestPayload;
import com.rocketseat.planner.activity.response.ActivityDataResponse;
import com.rocketseat.planner.activity.response.ActivityResponse;
import com.rocketseat.planner.activity.service.ActivityService;
import com.rocketseat.planner.trip.domain.Trip;
import com.rocketseat.planner.trip.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;
    private TripService tripService;

    @Override
    public ActivityResponse createActivity(ActivityRequestPayload payload, Trip trip) {
        Activity newActivity = new Activity(payload.title(), payload.occurs_at(), trip);
        activityRepository.save(newActivity);
        return new ActivityResponse(newActivity.getId());
    }

    public ActivityResponse registerActivity(UUID id, ActivityRequestPayload payload){
        Trip rawTrip = tripService.findById(id);
        return createActivity(payload, rawTrip);
    }

    @Override
    public List<ActivityDataResponse> getAllActivitiesFromTrip(UUID id) {
        List<Activity> allActivity = activityRepository.findAllByTripId(id);
        if (allActivity.isEmpty()) {
            throw new NoSuchElementException("No activities found for Trip ID: " + id);
        }
        return allActivity.stream().map(activity ->
                new ActivityDataResponse(activity.getId(),activity.getTitle(), activity.getOccursAt())).toList();
    }

}
