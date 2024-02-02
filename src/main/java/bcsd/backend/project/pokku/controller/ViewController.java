package bcsd.backend.project.pokku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping("/signin")
    public String viewSignin(){
        return "/signin";
    }

    @GetMapping("/signup")
    public String viewSignup(){
        return "/signup";
    }
}
