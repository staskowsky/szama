package pl.szama.meal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

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

    @Getter
    @Setter
    @Column(name="amount")
    private float amount;

    @Getter
    @Setter
    @Column(name = "portions")
    private Integer portions;
}
