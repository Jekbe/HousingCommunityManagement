package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reporting;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedTo;

    private String description;

    @Enumerated(EnumType.STRING)
    private FailureStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
