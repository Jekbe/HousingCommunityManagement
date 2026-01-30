package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
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

    private boolean enabled = true;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Manager managerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Resident residentProfile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message> sendMessages = new ArrayList<>();

    @OneToMany(mappedBy = "recipient")
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "recipient")
    private List<File> receivedFiles = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<File> sendFiles = new ArrayList<>();

    public boolean canSeePesel(User other){
        if (this.equals(other)){
            return true;
        }

        return this.role.isHigher(other.role);
    }

    public boolean isRoleEqualed(Role role){
        return this.getRole().equals(role);
    }
}
