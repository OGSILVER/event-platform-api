package md.usm.eventplatform.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.usm.eventplatform.dto.LocationDTO;
import md.usm.eventplatform.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDTO> create(@Valid @RequestBody LocationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAll() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getById(id));
    }
}
