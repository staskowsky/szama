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
import java.util.*;

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

    public String somethingWentWrong() {
        return "redirect:/?fatal";
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

    public void generateRandomDiet(User user, int expectedKcal) {
        checkDiet(user);
        for(int i=0;i<7;i++) {
            generateDay(user, i, expectedKcal);
        }
    }

    public void generateDay(User user, int dayIndex, int expectedKcal) {
        Day[] days = user.getDiet().getDays();
        MealPointer[] mealPointers = days[dayIndex].getMealPointers();
        for(int i=0;i<4;i++) {
            generateMeal(user, dayIndex, i, expectedKcal);
        }
        days[dayIndex].setCaloricity(mealPointers[0].getMeal().getKcal() * mealPointers[0].getQuantity() +
                mealPointers[1].getMeal().getKcal() * mealPointers[1].getQuantity()+
                mealPointers[2].getMeal().getKcal() * mealPointers[2].getQuantity()+
                mealPointers[3].getMeal().getKcal() * mealPointers[3].getQuantity());
    }


    public void generateMeal(User user, int dayIndex, int mealIndex, int expectedKcal) {
        Diet diet = user.getDiet();
        List<Meal> meals = mealRepository.findAll();
        List<Meal> selected = new ArrayList<>();
        List<Float> quantites = new ArrayList<>();
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
                quantites.add((float) 1);
            }
        }
        Day[] days = diet.getDays();
        MealPointer[] mealPointers = days[dayIndex].getMealPointers();

        //in case there are less than 3 available recipes,
        //try to find recipes with doubled or halved portions
        if(selected.size()<3) {
            for(int i=0;i<meals.size();i++) {
                if(meals.get(i).getKcal()*0.5<=maxCalories && meals.get(i).getKcal()*0.5>=minCalories) {
                    selected.add(meals.get(i));
                    quantites.add((float) 0.5);
                }
            }
            for(int i=0;i<meals.size();i++) {
                if(meals.get(i).getKcal()*2<=maxCalories && meals.get(i).getKcal()*2>=minCalories) {
                    selected.add(meals.get(i));
                    quantites.add((float) 2);
                }
            }
        }

        //in case there are no available recipes,
        //try to find recipe with kcals closest to required
        if (selected.isEmpty()) {
            boolean fromDoubles = false;
            boolean fromHalves = false;
            int chosenMeal = 0;
            int difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(0).getKcal());
            for(int i=1;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal())<difference) {
                    chosenMeal = i;
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                }
            }
            //looking for meal in double portions
            for(int i=0;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal()*2)<difference) {
                    chosenMeal = i;
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                    fromDoubles = true;
                }
            }
            //looking for meal in half portions
            for(int i=0;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal()*0.5)<difference) {
                    chosenMeal = i;
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                    fromDoubles = false;
                    fromHalves = true;
                }
            }
            mealPointers[mealIndex].setMeal(meals.get(chosenMeal));
            if(fromDoubles==false && fromHalves==false) {
                mealPointers[mealIndex].setQuantity(1);
            } else if (fromDoubles==true && fromHalves==false) {
                mealPointers[mealIndex].setQuantity(2);
            } else {
                mealPointers[mealIndex].setQuantity((float) 0.5);
            }
        } else {
            int random = (int) (Math.random()*selected.size());
            mealPointers[mealIndex].setMeal(selected.get(random));
            mealPointers[mealIndex].setQuantity(quantites.get(random));
        }

        if(mealPointers[0].getMeal()!=null && mealPointers[1].getMeal()!=null && mealPointers[2].getMeal()!=null && mealPointers[3].getMeal()!=null) {
            days[dayIndex].setCaloricity(mealPointers[0].getMeal().getKcal() * mealPointers[0].getQuantity() +
                    mealPointers[1].getMeal().getKcal() * mealPointers[1].getQuantity() +
                    mealPointers[2].getMeal().getKcal() * mealPointers[2].getQuantity() +
                    mealPointers[3].getMeal().getKcal() * mealPointers[3].getQuantity());
        }

        if(days[dayIndex].getCaloricity()<expectedKcal*0.95 || days[dayIndex].getCaloricity()>expectedKcal*1.05) {
            generateMealToMissingKcal(user, dayIndex, 1, expectedKcal);
        }

        dietRepository.save(diet);
    }

    public void generateMealToMissingKcal(User user, int dayIndex, int mealIndex, int expectedKcal) {
        Diet diet = user.getDiet();
        Day days[] = user.getDiet().getDays();
        MealPointer mealPointers[] = days[dayIndex].getMealPointers();
        List<Meal> meals = mealRepository.findAll();
        List<Meal> selected = new ArrayList<>();
        List<Float> quantites = new ArrayList<>();
        int minCalories = (int) (expectedKcal*0.95);
        int maxCalories = (int) (expectedKcal*1.05);
        for(int i=0;i<4;i++) {
            if(i!=mealIndex) {
                minCalories = minCalories - (int) (mealPointers[i].getMeal().getKcal()*mealPointers[i].getQuantity());
                maxCalories = maxCalories - (int) (mealPointers[i].getMeal().getKcal()*mealPointers[i].getQuantity());
            }
        }

        for(int i=0;i<meals.size();i++) {
            if(meals.get(i).getKcal()<=maxCalories && meals.get(i).getKcal()>=minCalories) {
                selected.add(meals.get(i));
                quantites.add((float) 1);
            }
        }

        if(selected.size()<3) {
            for(int i=0;i<meals.size();i++) {
                if(meals.get(i).getKcal()*0.5<=maxCalories && meals.get(i).getKcal()*0.5>=minCalories) {
                    selected.add(meals.get(i));
                    quantites.add((float) 0.5);
                }
            }
            for(int i=0;i<meals.size();i++) {
                if(meals.get(i).getKcal()*2<=maxCalories && meals.get(i).getKcal()*2>=minCalories) {
                    selected.add(meals.get(i));
                    quantites.add((float) 2);
                }
            }
        }

        //in case there are no available recipes,
        //try to find recipe with kcals closest to required
        if (selected.isEmpty()) {
            boolean fromDoubles = false;
            boolean fromHalves = false;
            int chosenMeal = 0;
            int difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(0).getKcal());
            for(int i=1;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal())<difference) {
                    chosenMeal = i;
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                    fromDoubles = false;
                    fromHalves = false;
                }
            }
            //looking for meal in double portions
            for(int i=0;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal()*2)<difference) {
                    chosenMeal = i;
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                    fromDoubles = true;
                    fromHalves = false;
                }
            }
            //looking for meal in half portions
            for(int i=0;i<meals.size();i++) {
                if(Math.abs(((minCalories+maxCalories)*0.5) - meals.get(i).getKcal()*0.5)<difference) {
                    chosenMeal = i;
                    difference = (int) Math.abs(((minCalories+maxCalories)*0.5) - meals.get(chosenMeal).getKcal());
                    fromDoubles = false;
                    fromHalves = true;
                }
            }
            mealPointers[mealIndex].setMeal(meals.get(chosenMeal));
            if(fromDoubles==false && fromHalves==false) {
                mealPointers[mealIndex].setQuantity(1);
            }
            else if (fromDoubles==true && fromHalves==false) {
                mealPointers[mealIndex].setQuantity(2);
            }
            else if (fromDoubles==false && fromHalves==true){
                mealPointers[mealIndex].setQuantity((float) 0.5);
            } else {
                somethingWentWrong();
            }
        } else {
            int random = (int) (Math.random()*selected.size());
            mealPointers[mealIndex].setMeal(selected.get(random));
            mealPointers[mealIndex].setQuantity(quantites.get(random));
        }

        if(mealPointers[0].getMeal()!=null && mealPointers[1].getMeal()!=null && mealPointers[2].getMeal()!=null && mealPointers[3].getMeal()!=null) {
            days[dayIndex].setCaloricity(mealPointers[0].getMeal().getKcal() * mealPointers[0].getQuantity() +
                    mealPointers[1].getMeal().getKcal() * mealPointers[1].getQuantity() +
                    mealPointers[2].getMeal().getKcal() * mealPointers[2].getQuantity() +
                    mealPointers[3].getMeal().getKcal() * mealPointers[3].getQuantity());
        }

        dietRepository.save(diet);
    }
}
