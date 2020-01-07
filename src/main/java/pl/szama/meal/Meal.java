package pl.szama.meal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "meals")
public class Meal {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "Musisz podać nazwę.")
    @Size(min=3, max=64, message = "Nazwa musi zawierać od 3 do 64 znaków.")
    private String name;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

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
    @Column(name="amount")
    @JsonIgnore
    private float amount;

    @Getter
    @Setter
    @Column(name = "portions")
    @Min(value = 0, message = "Wartość musi być dodatnia.")
    @JsonIgnore
    private int portions;
}
