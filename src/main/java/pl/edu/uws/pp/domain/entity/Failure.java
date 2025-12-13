package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Failure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reporting;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedTo;

    private String description;

    @OneToMany(mappedBy = "failure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Photo> photos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FailureStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
