package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.uws.pp.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apartment apartment;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime dueDate;
}
