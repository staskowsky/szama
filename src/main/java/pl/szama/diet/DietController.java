package pl.szama.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szama.meal.Meal;
import pl.szama.meal.MealRepository;
import pl.szama.user.User;
import pl.szama.user.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/diets")
public class DietController {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final DietRepository dietRepository;
    private final double BREAKFAST_MIN = 0.25;
    private final double BREAKFAST_MAX = 0.30;
    private final double LUNCH_MIN = 0.05;
    private final double LUNCH_MAX = 0.10;
    private final double DINNER_MIN = 0.35;
    private final double DINNER_MAX = 0.40;
    private final double SUPPER_MIN = 0.25;
    private final double SUPPER_MAX = 0.30;



    @Autowired
    public DietController(MealRepository mealRepository, UserRepository userRepository, DietRepository dietRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;
    }

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute( "user", user);
        return "diets/index";
    }

    public void checkDiet(User user) {
        if(user.getDiet()==null) {
            Diet diet = new Diet();
            diet.setUser(user);
            diet.setMinCaloricity((float) (user.getMetabolism().getKcalToHold()*0.9));
            diet.setMaxCaloricity((float) (user.getMetabolism().getKcalToHold()*1.1));
            user.setDiet(diet);
            Day[] days = new Day[7];
            for(int i=0;i<7;i++) {
                Day day = new Day();
                day.setDiet(diet);
                MealPointer[] mealPointers = new MealPointer[4];
                day.setMealPointers(mealPointers);
                for(int j=0;j<4;j++) {
                    MealPointer mealPointer = new MealPointer();
                    mealPointer.setDay(day);
                    mealPointers[j] = mealPointer;
                }
                days[i] = day;
            }
            diet.setDays(days);
        }
        dietRepository.save(user.getDiet());
        userRepository.save(user);
    }

    @GetMapping("/create")
    public String create() {
        return "diets/create";
    }

    @PostMapping("/generate/hold/auto")
    public String generateRandomHold(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        checkDiet(user);
        for(int i=0;i<7;i++) {
            generateDay(user, i);
        }
        return "redirect:/diets/?generatedSuccessfully";
    }

    public void generateMeal(User user, int dayIndex, int mealIndex) {
        Diet diet = user.getDiet();
        List<Meal> meals = mealRepository.findAll();
        List<Meal> selected = new ArrayList<>();
        int minCalories, maxCalories;
        System.out.println(user.getMetabolism().getKcalToHold()*0.9 + "-" + user.getMetabolism().getKcalToHold()*1.1);
        switch (mealIndex) {
            case 0: {
                minCalories = (int) (BREAKFAST_MIN*user.getMetabolism().getKcalToHold());
                maxCalories = (int) (BREAKFAST_MAX*user.getMetabolism().getKcalToHold());
                System.out.println("min: "+ minCalories);
                System.out.println("max: " + maxCalories);
                break;
            }
            case 1: {
                minCalories = (int) (LUNCH_MIN*user.getMetabolism().getKcalToHold());
                maxCalories = (int) (LUNCH_MAX*user.getMetabolism().getKcalToHold());
                System.out.println("min: "+ minCalories);
                System.out.println("max: " + maxCalories);
                break;
            }
            case 2: {
                minCalories = (int) (DINNER_MIN*user.getMetabolism().getKcalToHold());
                maxCalories = (int) (DINNER_MAX*user.getMetabolism().getKcalToHold());
                System.out.println("min: "+ minCalories);
                System.out.println("max: " + maxCalories);
                break;
            }
            case 3: {
                minCalories = (int) (SUPPER_MIN*user.getMetabolism().getKcalToHold());
                maxCalories = (int) (SUPPER_MAX*user.getMetabolism().getKcalToHold());
                System.out.println("min: "+ minCalories);
                System.out.println("max: " + maxCalories);
                break;
            }
            default:
                minCalories = 0;
                maxCalories = 0;
                break;
        }

        for(int i=0;i<meals.size();i++) {
            if(meals.get(i).getKcal()<=maxCalories && meals.get(i).getKcal()>=minCalories) {
                selected.add(meals.get(i));
            }
        }
        Day[] days = diet.getDays();
        MealPointer[] mealPointers = days[dayIndex].getMealPointers();
        if(selected.isEmpty()) {
            int chosenMeal = 0;
            int difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(0).getKcal());
            for(int i=1;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal())<difference) {
                    chosenMeal = i;
                }
            }
            mealPointers[mealIndex].setMeal(meals.get(chosenMeal));
        } else {
            int random = (int) (Math.random()*selected.size());
            mealPointers[mealIndex].setMeal(selected.get(random));
        }
        System.out.println(mealPointers[mealIndex].getMeal().getName() + ": " + mealPointers[mealIndex].getMeal().getKcal());
        dietRepository.save(diet);
    }

    public void generateDay(User user, int dayIndex) {
        for(int i=0;i<4;i++) {
            generateMeal(user, dayIndex, i);
            Day[] days = user.getDiet().getDays();
            MealPointer[] mealPointers = days[dayIndex].getMealPointers();
            days[dayIndex].setCaloricity(mealPointers[0].getMeal().getKcal() +
                    mealPointers[1].getMeal().getKcal() +
                    mealPointers[2].getMeal().getKcal() +
                    mealPointers[3].getMeal().getKcal());
        }
    }

}
