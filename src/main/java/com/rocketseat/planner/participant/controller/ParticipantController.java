package com.rocketseat.planner.participant.controller;


import com.rocketseat.planner.participant.domain.Participant;
import com.rocketseat.planner.participant.request.ParticipantRequestPayload;
import com.rocketseat.planner.participant.service.ParticipantService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
@AllArgsConstructor
@Log4j2
public class ParticipantController {

    private ParticipantService participantService;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload requestPayload) {
        try{
            Participant participant = participantService.confirmParticipant(id, requestPayload);
            return ResponseEntity.ok(participant);
        } catch(NoSuchElementException e){
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
