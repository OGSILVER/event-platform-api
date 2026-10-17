package md.usm.eventplatform.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegistrationDTO {
    private Long id;
    private Long eventId;
    private Long participantId;
    private LocalDateTime registeredAt;
}
