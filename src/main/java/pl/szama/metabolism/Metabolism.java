package pl.szama.metabolism;

import lombok.Getter;
import lombok.Setter;
import pl.szama.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="metabolism")
public class Metabolism {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "users", nullable = false)
    private User user;

    @Getter
    @Setter
    @Min(value = 30, message = "Podaj poprawną wartość.")
    @Max(value = 300, message = "Podaj poprawną wartość.")
    public Integer mass;

    @Getter
    @Setter
    @Min(value = 100, message = "Podaj poprawną wartość.")
    @Max(value = 250, message = "Podaj poprawną wartość.")
    public Integer height;

    @Getter
    @Setter
    public float bmi;

    @Getter
    @Setter
    @Min(value = 15, message = "Podaj poprawną wartość.")
    @Max(value = 120, message = "Podaj poprawną wartość.")
    public Integer age;

    @Getter
    @Setter
    public float activityLevel;

    @Getter
    @Setter
    public int sex;
    //value: males=1
    //       females=0
    //used as boolean

    @Getter
    @Setter
    public int bmr;

    @Getter
    @Setter
    public int kcalToLose;

    @Getter
    @Setter
    public int kcalToHold;

    @Getter
    @Setter
    public int kcalToGain;

}
