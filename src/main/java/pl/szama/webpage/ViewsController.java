package pl.szama.webpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {

    @GetMapping("/")
    public String homepage() {
        return "webpage/index";
    }
}
