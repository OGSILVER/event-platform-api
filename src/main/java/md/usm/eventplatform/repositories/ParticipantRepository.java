package md.usm.eventplatform.repositories;

import md.usm.eventplatform.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    boolean existsByEmail(String email);
}
