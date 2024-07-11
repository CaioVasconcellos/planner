package com.rocketseat.planner.link.service.impl;

import com.rocketseat.planner.link.domain.Link;
import com.rocketseat.planner.link.repository.LinkRepository;
import com.rocketseat.planner.link.request.LinkRequestPayload;
import com.rocketseat.planner.link.response.LinkDataResponse;
import com.rocketseat.planner.link.response.LinkResponse;
import com.rocketseat.planner.link.service.LinkService;
import com.rocketseat.planner.participant.response.ParticipantDataResponse;
import com.rocketseat.planner.trip.domain.Trip;
import com.rocketseat.planner.trip.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;
    private TripService tripService;


    @Override
    public LinkResponse createLink(LinkRequestPayload payload, Trip trip) {
        Link newLink = new Link(payload.title(), payload.url(), trip);
        linkRepository.save(newLink);
        return new LinkResponse(newLink.getId());


    }

    @Override
    public LinkResponse registerLink(UUID id, LinkRequestPayload payload) {
        Trip rawTrip = tripService.findById(id);
        return createLink(payload, rawTrip);
    }

    @Override
    public List<LinkDataResponse> getAllActivitiesFromTrip(UUID id) {
        List<Link> allLinksByTripId = linkRepository.findAllLinksByTripId(id);
        return allLinksByTripId.stream().map(links -> new LinkDataResponse(links.getId(),links.getTitle(), links.getUrl())).toList();
    }
}
