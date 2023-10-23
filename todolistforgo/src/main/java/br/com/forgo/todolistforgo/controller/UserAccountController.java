package br.com.forgo.todolistforgo.controller;

import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.model.UserAccount;
import br.com.forgo.todolistforgo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserAccountController {
    @Autowired
    private UserAccountRepository repository;
    @GetMapping
    public String home(Model model){
        model.addAttribute("user",
                new User());
        return "register";
    }
    @GetMapping("/login")
    public String signIn(){
        return "login";
    }
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/profile")
    public String userProfile(Authentication authentication, Model model) {
        UserAccount userAccount = repository
                .findUserAccountByUsername(authentication.getName())
                .stream()
                .findFirst()
                .orElseThrow(() -> new
                        UsernameNotFoundException("username not found"));
        model.addAttribute("user", userAccount.getUser());
        return "profile";
    }
}
