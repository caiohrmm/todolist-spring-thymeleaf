package br.com.forgo.todolistforgo.controller;

import br.com.forgo.todolistforgo.model.Task;
import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.model.UserAccount;
import br.com.forgo.todolistforgo.repository.UserAccountRepository;
import br.com.forgo.todolistforgo.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private UserServiceImpl service;


    @Autowired
    private UserAccountRepository userAccountRepository;

    @GetMapping("/createtask")
    public String home(Model model){
        model.addAttribute("task",
                new Task());
        return "createtask";
    }

    @PostMapping("/createtask")
    public String createTask(Task task, Authentication auth, Model model)  {
        UserAccount userAccount = userAccountRepository
                .findUserAccountByUsername(auth.getName())
                .stream()
                .findFirst()
                .orElseThrow(() -> new
                        UsernameNotFoundException("username not found"));
        User user = userAccount.getUser();

        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setDone(false);

        try {
            service.createTask(task);
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/?error";
        }
    }
}
