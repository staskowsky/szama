package pl.szama.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.szama.user.User;
import pl.szama.user.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Autowired
    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("product", products);
        model.addAttribute("category", Category.values());
        return "products/index";
    }

    @GetMapping("/create")
    public String create(Model model, Product product) {
        model.addAttribute("product", product);
        return "products/create";
    }

    @PostMapping("/create")
    public String store(Product product, Principal principal) {
        product.setUser(userRepository.findByUsername(principal.getName()));
        productRepository.save(product);
        return "redirect:/products?addingSuccessful";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.getOne(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/edit")
    public String update(Product product, Principal principal) {
        product.setUser(userRepository.findByUsername(principal.getName()));
        productRepository.save(product);
        return "redirect:/products?editSuccessful";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Product product = productRepository.getOne(id);
        productRepository.delete(product);
        return "redirect:/products?deleteSuccessful";
    }
}
