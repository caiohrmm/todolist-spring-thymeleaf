package br.com.forgo.todolistforgo.repository;

import br.com.forgo.todolistforgo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
