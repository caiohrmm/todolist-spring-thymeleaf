package br.com.forgo.todolistforgo.controller;

import br.com.forgo.todolistforgo.model.Task;
import br.com.forgo.todolistforgo.service.taskservice.TaskServiceImpl;
import br.com.forgo.todolistforgo.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImpl service;

    @Autowired
    public TaskController(TaskServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public ModelAndView findAllTasks() {
        ModelAndView modelAndView = new ModelAndView("tasks");
        List<Task> tasks = service.findAllTasks();
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

}
