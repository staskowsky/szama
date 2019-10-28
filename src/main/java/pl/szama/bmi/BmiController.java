package pl.szama.bmi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bmi")
public class BmiController {

    @GetMapping("/")
    public String index(Bmi bmi, Model model) {
        model.addAttribute("bmi", bmi);
        return "bmi/index";
    }

    @PostMapping("/result")
    public String result(Bmi bmi, Model model) {
        model.addAttribute("bmi", bmi);
        return "bmi/index";
    }
}
