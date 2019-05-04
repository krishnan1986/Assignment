package com.example.userLogin.userRegistration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FormController {

    @GetMapping("/confirm")
    public String displayLogin(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "confirm";
    }
    @PostMapping("/confirm")
    public String handleForm(@ModelAttribute UserModel user)
    {

        return "confirmed";
    }

}
