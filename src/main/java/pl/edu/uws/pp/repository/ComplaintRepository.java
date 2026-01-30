package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Complaint;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findAllByAssignedTo(Manager manager);
    List<Complaint> findAllByReporting(Resident resident);
    List<Complaint> findAllByReportingAndAssignedTo(Resident resident, Manager manager);
}
