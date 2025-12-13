package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.uws.pp.domain.enums.NotificationStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
