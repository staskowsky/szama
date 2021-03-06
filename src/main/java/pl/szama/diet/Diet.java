package pl.szama.diet;

import lombok.Getter;
import lombok.Setter;
import pl.szama.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name= "diet")
public class Diet {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Getter
    @Setter
    @OneToMany(mappedBy = "diet", cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
    @NotNull
    @OrderColumn
    private Day[] days;

    @Getter
    @Setter
    @Column
    private float minCaloricity;

    @Getter
    @Setter
    @Column
    private float maxCaloricity;
}
