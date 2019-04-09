package pl.szama.user;

import lombok.Getter;
import lombok.Setter;
import pl.szama.meal.Meal;

import javax.persistence.*;

@Entity
@Table(name="favoriteMeals")
public class Favorite {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user", nullable = false)
    private User user;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "meal", nullable = false)
    private Meal meal;
}
