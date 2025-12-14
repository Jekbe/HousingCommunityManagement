package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
