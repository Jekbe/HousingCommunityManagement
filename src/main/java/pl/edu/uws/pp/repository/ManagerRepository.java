package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
