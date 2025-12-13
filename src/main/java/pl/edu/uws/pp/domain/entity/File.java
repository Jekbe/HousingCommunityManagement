package pl.edu.uws.pp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.uws.pp.domain.enums.FileType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @CreationTimestamp
    private LocalDateTime uploadTime;

    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;
}
