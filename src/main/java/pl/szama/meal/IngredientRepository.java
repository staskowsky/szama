package pl.szama.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szama.product.Product;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByMeal(Meal meal);
    List<Ingredient> findAllByProduct(Product product);
}
