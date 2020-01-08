package pl.szama.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szama.meal.Meal;

import java.util.List;

public interface MealPointerRepository extends JpaRepository<MealPointer, Long> {
    List<MealPointer> findAllByMeal(Meal meal);
}
