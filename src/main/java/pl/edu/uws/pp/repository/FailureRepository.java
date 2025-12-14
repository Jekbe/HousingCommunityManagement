package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Failure;

public interface FailureRepository extends JpaRepository<Failure, Long> {
}
