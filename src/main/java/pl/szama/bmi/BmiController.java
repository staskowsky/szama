package pl.szama.bmi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bmi")
public class BmiController {

    @GetMapping("/")
    public String index() {
        return "bmi/index";
    }
}
