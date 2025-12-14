package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
