package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_id", nullable = false)
    private Resident reporting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager assignedTo;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FailureStatus status;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
