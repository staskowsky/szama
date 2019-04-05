package pl.szama.meal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meals")
public class MealController {

    @GetMapping("/")
    public String index() {
        return "/meals/index";
    }

    @GetMapping("/create")
    public String create() {
        return "/meals/create";
    }

    @PostMapping("/create")
    public String store() {
        return "redirect:/meals/index";
    }

    @GetMapping("/edit")
    public String edit() {
        return "/meals/edit";
    }

    @RequestMapping("/{id}")
    public Meal display(@PathVariable("id") Long id, Meal meal) { return meal; }
}
