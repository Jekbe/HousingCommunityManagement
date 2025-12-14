package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
