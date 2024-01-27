package HSAnimal.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

//    @GetMapping("/home")
//    public String home(Model model, @AuthenticationPrincipal User userInfo) throws Exception {
//        model.addAttribute("userInfo", userInfo);
//        return "home";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
