package br.com.forgo.todolistforgo.service.userservice;

import br.com.forgo.todolistforgo.exceptions.InvalidTaskException;
import br.com.forgo.todolistforgo.exceptions.TaskNotFoundException;
import br.com.forgo.todolistforgo.exceptions.UserExistingException;
import br.com.forgo.todolistforgo.exceptions.UserNotFoundException;
import br.com.forgo.todolistforgo.model.Task;
import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.repository.TaskRepository;
import br.com.forgo.todolistforgo.repository.UserRepository;
import br.com.forgo.todolistforgo.service.useraccountservice.UserAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserInterfaceService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserAccountService userAccountService;


    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User createUser(User user) {

        User userExisting = repository.findUserByEmail(user.getEmail());

        if (userExisting != null) {
            throw new UserExistingException("Usuário já existe !");
        }

        user.setCreatedUser(LocalDateTime.now());
        User theUser = repository.save(user);
        userAccountService.createUserAccount(theUser);
        return theUser;
    }

    @Override
    public List<Task> getAllTasksForUser(Long userId) {
        List<Task> tasks = taskRepository.findAllTasksByUserId(userId);

        if (tasks == null || tasks.isEmpty()) {
            throw new TaskNotFoundException("Não existem tarefas cadastradas para esse usuário.");
        }

        return tasks;
    }

    @Override
    public Task getTaskById(Long taskId, Long userId) {
       Task task = taskRepository.findTaskByUserId(userId, taskId);

       if (task == null) throw new TaskNotFoundException("A tarefa não existe!");

       return task;
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {

        // Verificar se a tarefa pertence ao usuário
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null || !(task.getUser().equals(userId))) {
            // Tarefa não encontrada ou não pertence ao usuário, pode lançar uma exceção personalizada ou retornar uma mensagem de erro
            throw new TaskNotFoundException("Tarefa não encontrada ou não pertence ao usuário");
        }
        // Excluir a tarefa
        taskRepository.deleteById(taskId);

    }

    @Override
    public Task updateTask(Long userId, Long taskId, Task updatedTask) {
        // Verificar se a tarefa existe e pertence ao usuário
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask == null || !existingTask.getUser().getUserId().equals(userId)) {
            throw new TaskNotFoundException("Tarefa não encontrada ou não pertence ao usuário");
        }

        // Atualizar os campos da tarefa com os novos valores
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompletionDate(updatedTask.getCompletionDate());
        existingTask.setDone(updatedTask.getDone());

        // Salvar a tarefa atualizada no banco de dados
        return taskRepository.save(existingTask);
    }

    @Override
    public Task createTask(Task task) {

        // Validar a tarefa antes de criar
        if (task.getTitle() == null || task.getTitle().isEmpty() ){
            throw new InvalidTaskException("O título da tarefa é obrigatório.");
        }

        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new InvalidTaskException("O título da tarefa é obrigatório.");
        }

        // Salvar a tarefa no banco de dados
        return taskRepository.save(task);
    }

    @Override
    public void completeTask(Long taskId, Long userId) {
        Task task = taskRepository.findTaskByUserId(userId, taskId);

        if (task == null || !task.getUser().getUserId().equals(userId)) {
            throw new TaskNotFoundException("Tarefa não encontrada ou não pertence ao usuário");
        }

        task.setCompletionDate(LocalDateTime.now());
        task.setDone(true);
        taskRepository.save(task);
    }

    @Transactional
    public void deleteCompletedTasks(Long userId) {

        if (userId == null) throw new UserNotFoundException("Usuário Inválido");

        taskRepository.deleteCompletedTasks(userId);


    }
}
