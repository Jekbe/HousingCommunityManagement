package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resident_apartment", joinColumns = @JoinColumn(name = "resident_id"), inverseJoinColumns = @JoinColumn(name = "apartment_id"))
    private List<Apartment> apartments = new ArrayList<>();

    @OneToMany(mappedBy = "reporting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Failure> reportedFailures = new ArrayList<>();

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "reporting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Complaint> complaints = new ArrayList<>();
}
