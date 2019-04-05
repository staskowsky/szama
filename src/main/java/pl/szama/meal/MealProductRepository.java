package pl.szama.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szama.product.Product;

import java.util.List;

@Repository
public interface MealProductRepository extends JpaRepository<MealProduct, Long> {
    List<Product> findAllByMeal(Meal meal);
}
