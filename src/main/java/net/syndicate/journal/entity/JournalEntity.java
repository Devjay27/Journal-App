package net.syndicate.journal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="journal")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="journal_id")
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name="title")
    private String title;

    @NotNull
    @Size(min = 10, max = 1000)
    @Column(name="content")
    private String content;

    @NotNull
    @Column(name="created")
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private UserEntity user;
}
