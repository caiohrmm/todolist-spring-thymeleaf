package br.com.forgo.todolistforgo.service.userservice;

import br.com.forgo.todolistforgo.model.Task;
import br.com.forgo.todolistforgo.model.User;

import java.util.List;

public interface UserInterfaceService {



    // Métodos para o usuário.
    User updateUser(User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);

    User createUser(User user);


    // Métodos para as tasks.
    List<Task> getAllTasksForUser(Long userId);
    Task getTaskById(Long taskId, Long userId);
    void deleteTask(Long taskId, Long userId);
    Task updateTask(Long taskId, Long userId, Task updatedTask);
    Task createTask(Task task);
}
