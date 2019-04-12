package pl.szama.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.szama.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Getter
    @Setter
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

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
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "owner", nullable = false)
    @JsonIgnore
    private User user;
}
