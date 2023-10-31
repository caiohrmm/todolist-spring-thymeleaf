package br.com.forgo.todolistforgo.controller;

import br.com.forgo.todolistforgo.model.Task;
import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.model.UserAccount;
import br.com.forgo.todolistforgo.repository.TaskRepository;
import br.com.forgo.todolistforgo.repository.UserAccountRepository;
import br.com.forgo.todolistforgo.service.userservice.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private UserServiceImpl service;


    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TaskRepository repository;


    @GetMapping("/createtask")
    public String home(Model model){
        model.addAttribute("task",
                new Task());
        return "createtask";
    }

    @GetMapping("/edittask/{taskId}/{userId}")
    public String editTask(Model model, @PathVariable Long taskId,
                       @PathVariable Long userId){
        Task task = repository.findTaskByUserId(taskId, userId);
        model.addAttribute("task", task
                );
        model.addAttribute("user",
                task.getUser());
        return "edittask";
    }

    @PostMapping("/edittask/{taskId}/{userId}")
    public String editTask(@PathVariable Long taskId,
                           @PathVariable Long userId,
                           @ModelAttribute("updatedTask")
                               Task updatedTask, Model model){

        service.updateTask(taskId, userId, updatedTask);

        return "redirect:/profile";
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

        try {
            service.createTask(task);
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/?error";
        }
    }

    @GetMapping("/completetask/{taskId}/{userId}")
    public String completeTask(@PathVariable Long taskId, @PathVariable Long userId) {

        service.completeTask(taskId, userId);

        return "redirect:/profile";


    }

    @GetMapping("/deletecompletedtasks/{userId}")
    public String completeTask(@PathVariable Long userId) {

        service.deleteCompletedTasks(userId);

        return "redirect:/profile";


    }

    @GetMapping("/deletetask/{taskId}/{userId}")
    public String deleteTaskById(@PathVariable Long taskId, @PathVariable Long userId) {

        service.deleteTask(taskId, userId);

        return "redirect:/profile";


    }
}
