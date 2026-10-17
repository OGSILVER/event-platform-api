package md.usm.eventplatform.services;

import md.usm.eventplatform.dto.ParticipantDTO;
import md.usm.eventplatform.exceptions.ResourceExistsException;
import md.usm.eventplatform.exceptions.ResourceNotFoundException;
import md.usm.eventplatform.models.Participant;
import md.usm.eventplatform.repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantDTO create(ParticipantDTO dto) {
        if (participantRepository.existsByEmail(dto.getEmail())) {
            throw new ResourceExistsException("A participant with this email already exists");
        }
        Participant participant = new Participant();
        participant.setName(dto.getName());
        participant.setEmail(dto.getEmail());
        return toDTO(participantRepository.save(participant));
    }

    public List<ParticipantDTO> getAll() {
        return participantRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ParticipantDTO getById(Long id) {
        return toDTO(participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant with this id does not exist")));
    }

    private ParticipantDTO toDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setName(participant.getName());
        dto.setEmail(participant.getEmail());
        return dto;
    }
}
