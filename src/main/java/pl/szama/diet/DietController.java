package pl.szama.diet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diet")
public class DietController {

    @GetMapping("/")
    public String index() {
        return "diets/index";
    }

    @GetMapping("/create")
    public String create() {
        return "diets/create";
    }
}
