package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Apartment;
import pl.edu.uws.pp.domain.entity.Building;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.Collection;
import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByBuildingIn(Collection<Building> buildings);
    List<Apartment> findAllByResidentsContaining(Resident resident);
    List<Apartment> findAllByResidentsContainingAndResidentsContaining(Resident resident, Resident resident2);
    List<Apartment> findAllByResidentsContainingAndBuilding_Manager(Resident resident, Manager manager);
}
