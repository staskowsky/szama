package pl.szama.metabolism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szama.user.User;
import pl.szama.user.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/metabolism")
public class MetabolismController {

    private final UserRepository userRepository;
    private final MetabolismRepository metabolismRepository;

    @Autowired
    public MetabolismController(UserRepository userRepository, MetabolismRepository metabolismRepository) {
        this.userRepository = userRepository;
        this.metabolismRepository = metabolismRepository;
    }

    @GetMapping("/")
    public String index(Metabolism metabolism, Model model) {
        model.addAttribute("metabolism", metabolism);
        return "metabolism/index";
    }

    @GetMapping("/result")
    public String displayResult(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if(user.getMetabolism()==null) {
            return "redirect:/metabolism/";
        }
        model.addAttribute("metabolism", user.getMetabolism());
        return "metabolism/result";
    }

    @PostMapping("/result")
    public String result(Model model, @Valid Metabolism metabolism, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "metabolism/index";
        }
        metabolism.bmi = metabolism.mass/(float)Math.pow((metabolism.height*0.01), 2);
        metabolism.bmr = (int) ((metabolism.mass*(float)9.99)+(metabolism.height*(float)6.25-((float)4.92*metabolism.age)+(166*metabolism.sex)));
        metabolism.kcalToHold = (int) (metabolism.bmr*metabolism.activityLevel);
        metabolism.kcalToLose = (int) (metabolism.kcalToHold*(float)0.85);
        metabolism.kcalToGain = (int) (metabolism.kcalToHold*(float)1.15);
        model.addAttribute("metabolism", metabolism);
        return "metabolism/result";
    }

    @PostMapping("/result/save")
    public String saveResult(Principal principal, Metabolism metabolism) {
        User user = userRepository.findByUsername(principal.getName());
        if (metabolismRepository.findByUser(user) != null) {
            metabolismRepository.delete(metabolismRepository.findByUser(user));
        }
        metabolism.setUser(user);
        user.setMetabolism(metabolism);
        metabolismRepository.save(metabolism);
        userRepository.save(user);
        return "redirect:/metabolism/?resultSaved";
    }
}
