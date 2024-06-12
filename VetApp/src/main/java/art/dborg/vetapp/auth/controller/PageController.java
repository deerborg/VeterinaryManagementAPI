package art.dborg.vetapp.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    @GetMapping("/customer")
    public String customer() {
        return "customer";
    }
    @GetMapping("/animal")
    public String animal() {
        return "animal";
    }
    @GetMapping("/vaccine")
    public String vaccine() {
        return "vaccine";
    }
    @GetMapping("/doctor")
    public String doctor() {
        return "doctor";
    }
    @GetMapping("/availableDate")
    public String availableDate() {
        return "availableDate";
    }
    @GetMapping("/appointment")
    public String appointment() {
        return "appointment";
    }
    @GetMapping("/allCustomer")
    public String allCustomer() {
        return "allCustomer";
    }
    @GetMapping("/updateCustomer")
    public String updateCustomer() {
        return "updateCustomer";
    }
    @GetMapping("/allAnimal")
    public String allAnimal() {
        return "allAnimal";
    }
    @GetMapping("/allDoctor")
    public String allDoctor() {
        return "allDoctor";
    }


}
