package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Event;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByBuilding_Manager(Manager manager);
    List<Event> findAllByBuilding_Apartments_ResidentsContains(Resident resident);
}
