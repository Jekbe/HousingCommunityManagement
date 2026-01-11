package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> sendMessages = new ArrayList<>();

    @OneToMany(mappedBy = "recipients", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> receivedMessages = new ArrayList<>();

    public boolean canSeePesel(User other){
        return this.role.isHigher(other.role);
    }
}
