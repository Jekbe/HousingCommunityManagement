package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
