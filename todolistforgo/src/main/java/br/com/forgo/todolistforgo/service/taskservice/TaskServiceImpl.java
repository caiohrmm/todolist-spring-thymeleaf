package br.com.forgo.todolistforgo.service.taskservice;

import br.com.forgo.todolistforgo.model.Task;
import br.com.forgo.todolistforgo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskInterfaceService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTask(Task task) {
        if (task.getId()!= null ) {
            throw new IllegalArgumentException("O ID deverá ser nulo antes de criar uma task.");
        }
        return repository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = repository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new IllegalArgumentException("Tarefa não encontrada com o ID fornecido.");
        }

        // Verifique se os campos não estão vazios antes de atualizar
        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getCreatedAt() != null) {
            existingTask.setCreatedAt(updatedTask.getCreatedAt());
        }
        if (updatedTask.getDone() != null) {
            existingTask.setDone(updatedTask.getDone());
        }

        return repository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task existingTask = repository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new IllegalArgumentException("Tarefa não encontrada com o ID fornecido.");
        }
        repository.deleteById(id);
    }

    @Override
    public Task findById(Long id) {
        Optional<Task> task = repository.findById(id);
        return task.orElse(null);
    }

    @Override
    public List<Task> findAllTasks() {
        return repository.findAll();
    }
}
