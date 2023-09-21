package br.com.forgo.todolistforgo.service.taskservice;

import br.com.forgo.todolistforgo.model.Task;

import java.util.List;

public interface TaskInterfaceService {
    Task createTask(Task task);
    Task updateTask(Long id, Task updatedTask);
    void deleteTask(Long id);
    Task findById(Long id);
    List<Task> findAllTasks();
}
