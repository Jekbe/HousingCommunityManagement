package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.uws.pp.domain.enums.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String pesel;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Manager managerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Resident residentProfile;
}
