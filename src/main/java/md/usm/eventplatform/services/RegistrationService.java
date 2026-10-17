package md.usm.eventplatform.services;

import md.usm.eventplatform.dto.RegistrationDTO;
import md.usm.eventplatform.exceptions.ResourceExistsException;
import md.usm.eventplatform.exceptions.ResourceNotFoundException;
import md.usm.eventplatform.models.Registration;
import md.usm.eventplatform.repositories.EventRepository;
import md.usm.eventplatform.repositories.RegistrationRepository;
import md.usm.eventplatform.repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    public RegistrationDTO register(Long eventId, Long participantId) {
        if (registrationRepository.existsByEventIdAndParticipantId(eventId, participantId)) {
            throw new ResourceExistsException("This participant is already registered for this event");
        }
        Registration registration = new Registration();
        registration.setEvent(eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event with this id does not exist")));
        registration.setParticipant(participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant with this id does not exist")));
        return toDTO(registrationRepository.save(registration));
    }

    public List<RegistrationDTO> getForEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("Event with this id does not exist");
        }
        return registrationRepository.findByEventId(eventId).stream().map(this::toDTO).toList();
    }

    private RegistrationDTO toDTO(Registration registration) {
        RegistrationDTO dto = new RegistrationDTO();
        dto.setId(registration.getId());
        dto.setEventId(registration.getEvent().getId());
        dto.setParticipantId(registration.getParticipant().getId());
        dto.setRegisteredAt(registration.getRegisteredAt());
        return dto;
    }
}
