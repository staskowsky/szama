package pl.szama.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            kcal = kcal + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getProtein();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            protein = protein + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getCarb();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            carb = carb + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getFat();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, MealProduct mealProduct) {
        Meal meal = mealRepository.getOne(id);
        List<MealProduct> linkedProduct = mealProductRepository.findAllByMeal(meal);
        List<Product> product = productRepository.findAll();
        model.addAttribute("meal", meal);
        model.addAttribute("linkedProduct", linkedProduct);
        model.addAttribute("mealProduct", mealProduct);
        model.addAttribute("product", product);
        return "meals/edit";
    }

    @PostMapping("/edit/product/add")
    public String addProduct(Model model, MealProduct mealProduct, Meal meal) {
        mealProduct.setMeal(meal);
        mealProductRepository.save(mealProduct);
        List<Product> products = productRepository.findAll();
        List<MealProduct> linkedProducts = mealProductRepository.findAllByMeal(meal);
        model.addAttribute("meal", meal);
        model.addAttribute("mealProduct", mealProduct);
        model.addAttribute("linkedProduct", linkedProducts);
        model.addAttribute("product", products);
        return "redirect:/meals/edit/" + meal.getId();
    }

    @PostMapping("/edit")
    public String update(Meal meal) {
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
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            kcal = kcal + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getProtein();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            protein = protein + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getCarb();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            carb = carb + productValue;
        }
        for(int i=0;i<linkedProducts.size();i++) {
            float productValue = linkedProducts.get(i).getProduct().getFat();
            productValue = productValue * ((linkedProducts.get(i).getAmount())/(100*meal.getPortions()));
            fat = fat + productValue;
        }
        meal.setAmount(amount);
        meal.setKcal(kcal);
        meal.setProtein(protein);
        meal.setFat(fat);
        meal.setCarb(carb);
        mealRepository.save(meal);
        return "redirect:/meals?editSuccessful";
    }

    @PostMapping("/delete/{id}")
    public String deleteMeal(@PathVariable("id") Long id) {
        Meal meal = mealRepository.getOne(id);
        List<MealProduct> mealProducts = mealProductRepository.findAllByMeal(meal);
        for(int i=0;i<mealProducts.size();i++) {
            mealProductRepository.delete(mealProducts.get(i));
        }
        mealRepository.delete(meal);
        return "redirect:/meals?deleteSuccessful";
    }

    @PostMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model, MealProduct mealProduct) {
        MealProduct mealProductToDelete = mealProductRepository.getOne(id);
        Meal meal = mealRepository.getOne(mealProductToDelete.getMeal().getId());
        mealProductRepository.delete(mealProductToDelete);
        List<MealProduct> linkedProduct = mealProductRepository.findAllByMeal(meal);
        model.addAttribute("meal", meal);
        model.addAttribute("linkedProduct", linkedProduct);
        model.addAttribute("mealProduct", mealProduct);
        return "redirect:/meals/edit/" + meal.getId();
    }

}
