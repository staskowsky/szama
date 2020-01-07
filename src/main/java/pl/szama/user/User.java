package pl.szama.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.szama.diet.Diet;
import pl.szama.metabolism.Metabolism;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message = "Pole nie może być puste.")
    @Column(name = "username", nullable = false, unique = true)
    @Size(min=3,max=64,message = "Musi zawierać od 3 do 64 znaków.")
    private String username;

    @Getter
    @Setter
    @NotNull(message = "Pole nie może być puste.")
    @Email(message = "Wprowadź poprawny adres e-mail.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @NotNull(message = "Pole nie może być puste.")
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "role", nullable = false)
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "metabolism")
    private Metabolism metabolism;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "diet")
    private Diet diet;
}
