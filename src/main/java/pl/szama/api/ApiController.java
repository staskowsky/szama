package pl.szama.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szama.meal.Meal;
import pl.szama.meal.Ingredient;
import pl.szama.meal.IngredientRepository;
import pl.szama.meal.MealRepository;
import pl.szama.product.Product;
import pl.szama.product.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ApiController(MealRepository mealRepository, IngredientRepository ingredientRepository,
                         ProductRepository productRepository) {
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Long id) {
        return productRepository.getOne(id);
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @RequestMapping(value = "/meals/{id}", method = RequestMethod.GET)
    public Meal getMealById(@PathVariable("id") Long id) {
        return mealRepository.getOne(id);
    }

    @RequestMapping(value = "/mealproducts/{id}", method = RequestMethod.GET)
    public List<Ingredient> getMealProductByMealId(@PathVariable("id") Long id) {
        return ingredientRepository.findAllByMeal(mealRepository.getOne(id));
    }
}
