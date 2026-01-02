package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false, unique = true)
    private Invoice invoice;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime paidAt;

    @Column(nullable = false)
    private String transactionId;
}
