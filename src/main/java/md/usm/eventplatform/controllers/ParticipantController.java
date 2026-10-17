package md.usm.eventplatform.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.usm.eventplatform.dto.ParticipantDTO;
import md.usm.eventplatform.services.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping
    public ResponseEntity<ParticipantDTO> create(@Valid @RequestBody ParticipantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getAll() {
        return ResponseEntity.ok(participantService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getById(id));
    }
}
