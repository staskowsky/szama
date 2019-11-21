package pl.szama.metabolism;

import lombok.Getter;
import lombok.Setter;
import pl.szama.user.User;

import javax.persistence.*;

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
    public Integer mass;

    @Getter
    @Setter
    public Integer height;

    @Getter
    @Setter
    public float bmi;

    @Getter
    @Setter
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
