package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
