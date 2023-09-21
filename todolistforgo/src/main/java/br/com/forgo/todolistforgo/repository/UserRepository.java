package br.com.forgo.todolistforgo.repository;

import br.com.forgo.todolistforgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
