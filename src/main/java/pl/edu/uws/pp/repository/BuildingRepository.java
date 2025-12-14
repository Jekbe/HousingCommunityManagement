package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
