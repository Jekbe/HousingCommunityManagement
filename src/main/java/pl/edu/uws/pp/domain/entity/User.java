package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Building> managedBuildings = new ArrayList<>();

    @ManyToMany(mappedBy = "apartments", fetch = FetchType.LAZY)
    private List<Apartment> apartments = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> sentMessages = new ArrayList<>();

    @ManyToMany(mappedBy = "recipients", fetch = FetchType.LAZY)
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "reporting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Failure> reportedFailures = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTo", fetch = FetchType.LAZY)
    private List<Failure> assignedFailures = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> uploadedFiles = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> receivedFiles = new ArrayList<>();

    private boolean enabled;
}
