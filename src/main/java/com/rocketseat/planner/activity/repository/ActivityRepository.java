package com.rocketseat.planner.activity.repository;

import com.rocketseat.planner.activity.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    @Query("SELECT a FROM Activity a WHERE a.trip.id = :tripIdActivity")
    List<Activity> findAllByTripId(@Param("tripIdActivity") UUID tripId);
}

