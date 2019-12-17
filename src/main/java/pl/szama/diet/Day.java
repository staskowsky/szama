package pl.szama.diet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "day")
public class Day {
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
    @OneToMany(mappedBy = "day", cascade = {CascadeType.ALL})
    @OrderColumn
    private MealPointer[] mealPointers;

    @Getter
    @Setter
    @Column
    private float caloricity;
}
