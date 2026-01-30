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
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Building> managedBuildings = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Failure> assignedFailures = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTo", fetch = FetchType.LAZY)
    private List<Complaint> managedComplaints = new ArrayList<>();

    public boolean isNotManagingApartment(Apartment apartment) {
        return this.getManagedBuildings()
                .stream()
                .noneMatch(building ->
                        building.getApartments()
                                .contains(apartment)
                );
    }
}
