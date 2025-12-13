package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "message_recipient", joinColumns = @JoinColumn(name = "message_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> recipients = new ArrayList<>();

    private String subject;

    private String content;

    @CreationTimestamp
    private LocalDateTime sendAt;
}
