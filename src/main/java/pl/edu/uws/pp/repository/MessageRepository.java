package pl.edu.uws.pp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uws.pp.domain.entity.Message;
import pl.edu.uws.pp.domain.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderOrRecipientOrderBySendAtDesc(User sender, User receiver);
    List<Message> findAllBySenderAndRecipientOrSenderAndRecipientOrderBySendAtDesc(User sender1, User recipient1, User sender2, User recipient2);
}
