package pl.szama.diet;

import lombok.Getter;
import lombok.Setter;
import pl.szama.meal.Meal;

import javax.persistence.*;

@Entity
@Table(name = "day")
public class Plan {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "diet", nullable = false)
    private Diet diet;

    @Getter
    @Setter
    @OneToOne
    private Meal breakfast;

    @Getter
    @Setter
    @OneToOne
    private Meal snack;

    @Getter
    @Setter
    @OneToOne
    private Meal lunch;

    @Getter
    @Setter
    @OneToOne
    private Meal supper;

}
