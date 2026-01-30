package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Failure;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.List;

public interface FailureRepository extends JpaRepository<Failure, Long> {
    List<Failure> findAllByAssignedTo(Manager manager);
    List<Failure> findAllByApartment_ResidentsContaining(Resident resident);

    List<Failure> findAllByReportingAndAssignedTo(Resident resident, Manager manager);
}
