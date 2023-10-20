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
@RequestMapping
public class UserController {
    @Autowired
    private UserServiceImpl service;



}
