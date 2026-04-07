package net.syndicate.journal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Table(name="user", indexes = @Index(columnList = "username"))
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NotNull
    @Size(min=2, max=50)
    @Column(name="firstname")
    private String firstname;

    @NotNull
    @Size(min=2, max=50)
    @Column(name="lastname")
    private String lastname;

    @NotNull
    @Size(min=2, max=50)
    @Column(name="email")
    private String email;

    @NotNull
    @Size(min=2, max=50)
    @Column(name="username")
    private String username;

    @NotNull
    @Size(min=8, max=50)
    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JournalEntity> journals = new ArrayList<>();
}
