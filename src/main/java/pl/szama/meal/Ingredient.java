package pl.szama.meal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.szama.product.Product;

import javax.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {
    @Getter
    @Setter
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "meal", nullable = false)
    private Meal meal;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;
    @Getter
    @Setter
    @Column(name="amount")
    private float amount;
    @Getter
    @Setter
    @Column(name="volume")
    private String volume;
}
