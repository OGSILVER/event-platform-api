package md.usm.eventplatform.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import md.usm.eventplatform.dto.RegistrationDTO;
import md.usm.eventplatform.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events/{eventId}/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDTO> register(
            @PathVariable Long eventId,
            @RequestParam @NotNull Long participantId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registrationService.register(eventId, participantId));
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> getForEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(registrationService.getForEvent(eventId));
    }
}
