package br.com.forgo.todolistforgo.controller;

import br.com.forgo.todolistforgo.dto.UserRegistrationDto;
import br.com.forgo.todolistforgo.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class AuthController {

    @Autowired
    private UserServiceImpl service;

    /*
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }
     */

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")
                                      UserRegistrationDto  registrationDto) {

        service.save(registrationDto);

        return "redirect:/register?success";

    }

}
