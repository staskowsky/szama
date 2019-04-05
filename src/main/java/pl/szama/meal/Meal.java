package pl.szama.meal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String name;

    @Getter
    @Setter
    @Column(name = "kcal")
    private float kcal;

    @Getter
    @Setter
    @Column(name = "protein")
    private float protein;

    @Getter
    @Setter
    @Column(name = "carb")
    private float carb;

    @Getter
    @Setter
    @Column(name = "fat")
    private float fat;
}
