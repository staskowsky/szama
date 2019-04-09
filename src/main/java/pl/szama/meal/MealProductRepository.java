package pl.szama.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealProductRepository extends JpaRepository<MealProduct, Long> {
    List<MealProduct> findAllByMeal(Meal meal);
}
