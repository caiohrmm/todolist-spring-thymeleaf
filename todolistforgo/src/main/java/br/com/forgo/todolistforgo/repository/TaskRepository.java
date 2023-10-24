package br.com.forgo.todolistforgo.repository;

import br.com.forgo.todolistforgo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user.userId = :userId")
    List<Task> findAllTasksByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.user.userId = :userId AND t.id = :taskId")
    Task findTaskByUserId(@Param("userId")Long userId, @Param("taskId") Long taskId);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.user.userId = :userId AND t.done")
    void deleteCompletedTasks(@Param("userId")Long userId);

}
