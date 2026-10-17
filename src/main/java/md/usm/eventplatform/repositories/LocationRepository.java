package md.usm.eventplatform.repositories;

import md.usm.eventplatform.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
