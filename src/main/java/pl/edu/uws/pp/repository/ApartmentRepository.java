package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
