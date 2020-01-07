package pl.szama.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.szama.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @NotNull(message = "Pole nie może być puste.")
    @Column(name = "name", nullable = false, unique = true)
    @Size(min=3, max=64, message = "Podaj prawidłową nazwę, musi zawierać przynajmniej trzy znaki.")
    private String name;

    @Getter
    @Setter
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Getter
    @Setter
    @Column(name = "kcal")
    @Min(value = 0, message = "Wartość musi być dodatnia.")
    private float kcal;

    @Getter
    @Setter
    @Column(name = "protein")
    @Min(value = 0, message = "Wartość musi być dodatnia.")
    @Max(value = 100, message = "Wartość podajemy w przeliczeniu na 100g produktu.")
    private float protein;

    @Getter
    @Setter
    @Column(name = "carb")
    @Min(value = 0, message = "Wartość musi być dodatnia.")
    @Max(value = 100, message = "Wartość podajemy w przeliczeniu na 100g produktu.")
    private float carb;

    @Getter
    @Setter
    @Column(name = "fat")
    @Min(value = 0, message = "Wartość musi być dodatnia.")
    @Max(value = 100, message = "Wartość podajemy w przeliczeniu na 100g produktu.")
    private float fat;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "owner", nullable = false)
    @JsonIgnore
    private User user;
}
