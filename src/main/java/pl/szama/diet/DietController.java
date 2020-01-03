package pl.szama.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/create")
    public String create(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute( "user", user);
        return "diets/create";
    }


    @PostMapping("/generate/auto/lose")
    public String generateRandomLose(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        int expectedKcal = user.getMetabolism().getKcalToLose();
        generateRandomDiet(user, expectedKcal);
        return "redirect:/diets/?generatedSuccessfully";
    }

    @PostMapping("/generate/auto/hold")
    public String generateRandomHold(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        int expectedKcal = user.getMetabolism().getKcalToHold();
        generateRandomDiet(user, expectedKcal);
        return "redirect:/diets/?generatedSuccessfully";
    }

    @PostMapping("/generate/auto/gain")
    public String generateRandomGain(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        int expectedKcal = user.getMetabolism().getKcalToGain();
        generateRandomDiet(user, expectedKcal);
        return "redirect:/diets/?generatedSuccessfully";
    }

    @PostMapping("/generate/auto")
    public String generateRandomKcalGiven(Principal principal, @RequestParam int expectedKcal) {
        User user = userRepository.findByUsername(principal.getName());
        generateRandomDiet(user, expectedKcal);
        return "redirect:/diets/?generatedSuccessfully";
    }

    public void generateRandomDiet(User user, int expectedKcal) {
        checkDiet(user);
        for(int i=0;i<7;i++) {
            generateDay(user, i, expectedKcal);
        }
    }

    public void checkDiet(User user) {
        if(user.getDiet()==null) {
            Diet diet = new Diet();
            diet.setUser(user);
            diet.setMinCaloricity((float) (user.getMetabolism().getKcalToHold()*0.95));
            diet.setMaxCaloricity((float) (user.getMetabolism().getKcalToHold()*1.05));
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

    public void generateMeal(User user, int dayIndex, int mealIndex, int expectedKcal) {
        Diet diet = user.getDiet();
        List<Meal> meals = mealRepository.findAll();
        List<Meal> selected = new ArrayList<>();
        int minCalories, maxCalories;
        switch (mealIndex) {
            case 0: {
                minCalories = (int) (BREAKFAST_MIN*expectedKcal);
                maxCalories = (int) (BREAKFAST_MAX*expectedKcal);
                break;
            }
            case 1: {
                minCalories = (int) (LUNCH_MIN*expectedKcal);
                maxCalories = (int) (LUNCH_MAX*expectedKcal);
                break;
            }
            case 2: {
                minCalories = (int) (DINNER_MIN*expectedKcal);
                maxCalories = (int) (DINNER_MAX*expectedKcal);
                break;
            }
            case 3: {
                minCalories = (int) (SUPPER_MIN*expectedKcal);
                maxCalories = (int) (SUPPER_MAX*expectedKcal);
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
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                }
            }
            mealPointers[mealIndex].setMeal(meals.get(chosenMeal));
        } else {
            int random = (int) (Math.random()*selected.size());
            mealPointers[mealIndex].setMeal(selected.get(random));
        }
        System.out.println(mealPointers[mealIndex].getMeal().getName() + ": " + mealPointers[mealIndex].getMeal().getKcal());
        days[dayIndex].setCaloricity(mealPointers[0].getMeal().getKcal() +
                mealPointers[1].getMeal().getKcal() +
                mealPointers[2].getMeal().getKcal() +
                mealPointers[3].getMeal().getKcal());
        dietRepository.save(diet);
    }

    public void generateDay(User user, int dayIndex, int expectedKcal) {
        for(int i=0;i<4;i++) {
            generateMeal(user, dayIndex, i, expectedKcal);
            Day[] days = user.getDiet().getDays();
            MealPointer[] mealPointers = days[dayIndex].getMealPointers();
            days[dayIndex].setCaloricity(mealPointers[0].getMeal().getKcal() +
                    mealPointers[1].getMeal().getKcal() +
                    mealPointers[2].getMeal().getKcal() +
                    mealPointers[3].getMeal().getKcal());
        }
    }

}
