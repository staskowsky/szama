package pl.szama.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/")
    public String index() {
        return "products/index";
    }

    @GetMapping("/create")
    public String create() {
        return "products/create";
    }

    @PostMapping("/create")
    public String store() {
        return "redirect:/products/index";
    }

    @GetMapping("/edit")
    public String edit() {
        return "products/edit";
    }
}
