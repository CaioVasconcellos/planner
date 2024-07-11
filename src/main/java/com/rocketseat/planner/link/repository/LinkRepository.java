package com.rocketseat.planner.link.repository;

import com.rocketseat.planner.link.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {


    @Query ("SELECT l FROM Link l WHERE l.trip.id = :tripIdLinks")
    List<Link> findAllLinksByTripId(@Param("tripIdLinks") UUID tripId);

}
