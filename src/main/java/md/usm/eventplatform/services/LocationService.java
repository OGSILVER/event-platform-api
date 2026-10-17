package md.usm.eventplatform.services;

import md.usm.eventplatform.dto.LocationDTO;
import md.usm.eventplatform.exceptions.ResourceNotFoundException;
import md.usm.eventplatform.models.Location;
import md.usm.eventplatform.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationDTO create(LocationDTO dto) {
        Location location = new Location();
        copyFields(dto, location);
        return toDTO(locationRepository.save(location));
    }

    public List<LocationDTO> getAll() {
        return locationRepository.findAll().stream().map(this::toDTO).toList();
    }

    public LocationDTO getById(Long id) {
        return toDTO(locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location with this id does not exist")));
    }

    private void copyFields(LocationDTO dto, Location location) {
        location.setName(dto.getName());
        location.setAddress(dto.getAddress());
        location.setCity(dto.getCity());
    }

    private LocationDTO toDTO(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setAddress(location.getAddress());
        dto.setCity(location.getCity());
        return dto;
    }
}
