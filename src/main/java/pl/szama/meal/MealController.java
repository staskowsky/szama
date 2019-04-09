package pl.szama.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szama.product.Product;
import pl.szama.product.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/meals")
public class MealController {

    private final MealRepository mealRepository;
    private final MealProductRepository mealProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public MealController(MealRepository mealRepository, MealProductRepository mealProductRepository,
                          ProductRepository productRepository) {
        this.mealRepository = mealRepository;
        this.mealProductRepository = mealProductRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Meal> meals = mealRepository.findAll();
        List<MealProduct> mealProducts = mealProductRepository.findAll();
        model.addAttribute("meal", meals);
        model.addAttribute("mealproduct", mealProducts);
        return "meals/index";
    }

    @GetMapping("/create/name")
    public String createMeal(Model model, Meal meal) {
        model.addAttribute("meal", meal);
        return "meals/create";
    }

    @PostMapping("/create/new")
    public String storeMeal(Meal meal, Model model, MealProduct mealProduct) {
        mealRepository.save(meal);
        List<Product> products = productRepository.findAll();
        List<MealProduct> linkedProducts = mealProductRepository.findAllByMeal(meal);
        model.addAttribute("meal", meal);
        model.addAttribute("mealProduct", mealProduct);
        model.addAttribute("linkedProducts", linkedProducts);
        model.addAttribute("product", products);
        return "meals/ingredients";
    }

    @PostMapping("/create/product")
    public String storeProduct(Meal meal, Model model, MealProduct mealProduct) {
        mealProductRepository.save(mealProduct);
        List<Product> products = productRepository.findAll();
        List<MealProduct> linkedProducts = mealProductRepository.findAllByMeal(meal);
        mealRepository.save(meal);
        model.addAttribute("meal", meal);
        model.addAttribute("mealProduct", mealProduct);
        model.addAttribute("linkedProduct", linkedProducts);
        model.addAttribute("product", products);
        return "meals/ingredients";
    }

    @PostMapping("/create/store")
    public String finalize(Meal meal) {
        List<MealProduct> linkedProducts = mealProductRepository.findAllByMeal(meal);
        float amount = 0;
        float kcal = 0;
        float protein = 0;
        float carb = 0;
        float fat = 0;
        for(int i=0;i<linkedProducts.size();i++) {
            amount += linkedProducts.get(i).getAmount();
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getKcal();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/100);
            kcal = kcal + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getProtein();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/100);
            protein = protein + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getCarb();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/100);
            carb = carb + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getFat();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/100);
            fat = fat + productValue;
        }
        meal.setAmount(amount);
        meal.setKcal(kcal);
        meal.setProtein(protein);
        meal.setFat(fat);
        meal.setCarb(carb);
        mealRepository.save(meal);
        return "redirect:/meals?addingSuccessful";
    }

    @GetMapping("/edit")
    public String edit() {
        return "meals/edit";
    }

}
