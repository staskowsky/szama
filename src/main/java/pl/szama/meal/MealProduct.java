package pl.szama.meal;

import lombok.Getter;
import lombok.Setter;
import pl.szama.product.Product;

import javax.persistence.*;

@Entity
@Table(name="mealProducts")
public class MealProduct {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "meal", nullable = false)
    private Meal meal;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product", nullable = false)
    private Product product;
    @Getter
    @Setter
    @Column(name="weight")
    private float weight;
    @Getter
    @Setter
    @Column(name="amount")
    private float amount;
}
