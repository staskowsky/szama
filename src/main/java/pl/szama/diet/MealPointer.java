package pl.szama.diet;

import lombok.Getter;
import lombok.Setter;
import pl.szama.meal.Meal;

import javax.persistence.*;

@Entity
@Table
public class MealPointer {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "day", nullable = false)
    private Day day;

    @Getter
    @Setter
    @OneToOne
    private Meal meal;

}
