package com.rocketseat.planner.link.service;

import com.rocketseat.planner.link.domain.Link;
import com.rocketseat.planner.link.request.LinkRequestPayload;
import com.rocketseat.planner.link.response.LinkDataResponse;
import com.rocketseat.planner.link.response.LinkResponse;
import com.rocketseat.planner.trip.domain.Trip;

import java.util.List;
import java.util.UUID;

public interface LinkService {

    LinkResponse createLink(LinkRequestPayload payload, Trip trip);

    LinkResponse registerLink(UUID id, LinkRequestPayload payload);

    List<LinkDataResponse> getAllActivitiesFromTrip(UUID id);
}
