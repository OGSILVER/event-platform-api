package md.usm.eventplatform.services;

import md.usm.eventplatform.dto.EventDTO;
import md.usm.eventplatform.exceptions.ResourceNotFoundException;
import md.usm.eventplatform.models.Event;
import md.usm.eventplatform.repositories.EventRepository;
import md.usm.eventplatform.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventDTO create(EventDTO dto) {
        Event event = new Event();
        copyFields(dto, event);
        return toDTO(eventRepository.save(event));
    }

    public List<EventDTO> getAll() {
        return eventRepository.findAll().stream().map(this::toDTO).toList();
    }

    public EventDTO getById(Long id) {
        return toDTO(findEvent(id));
    }

    public EventDTO update(Long id, EventDTO dto) {
        Event event = findEvent(id);
        copyFields(dto, event);
        return toDTO(eventRepository.save(event));
    }

    public Long delete(Long id) {
        findEvent(id);
        eventRepository.deleteById(id);
        return id;
    }

    private Event findEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with this id does not exist"));
    }

    private void copyFields(EventDTO dto, Event event) {
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());
        event.setLocation(locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location with this id does not exist")));
    }

    private EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartDate(event.getStartDate());
        dto.setEndDate(event.getEndDate());
        dto.setLocationId(event.getLocation().getId());
        return dto;
    }
}
