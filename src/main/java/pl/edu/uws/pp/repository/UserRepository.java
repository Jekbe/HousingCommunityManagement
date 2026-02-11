package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.User;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRole(Role role);
    Optional<User> findByEmail(String email);
    List<User> findDistinctByResidentProfile_Apartments_Building_Manager(Manager manager);
}
