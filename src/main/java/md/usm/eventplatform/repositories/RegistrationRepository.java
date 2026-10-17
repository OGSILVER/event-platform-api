package md.usm.eventplatform.repositories;

import md.usm.eventplatform.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByEventIdAndParticipantId(Long eventId, Long participantId);
    List<Registration> findByEventId(Long eventId);
}
