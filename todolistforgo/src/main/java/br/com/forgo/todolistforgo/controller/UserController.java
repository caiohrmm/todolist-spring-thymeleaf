package br.com.forgo.todolistforgo.controller;

import br.com.forgo.todolistforgo.exceptions.UserExistingException;
import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("api/v1/users")

public class UserController {

    @Autowired
    private UserServiceImpl service;
    @PostMapping("/register")
    public String createUser(@ModelAttribute("user")
                                 User user) {

        try {
            service.createUser(user);
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/?error";
        }



    }
}