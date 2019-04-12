package pl.szama.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @NotNull
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "role", nullable = false)
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;
}
