package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Building;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAllByManager(Manager manager);
    List<Building> findAllByApartments_ResidentsContains(Resident resident);
}
