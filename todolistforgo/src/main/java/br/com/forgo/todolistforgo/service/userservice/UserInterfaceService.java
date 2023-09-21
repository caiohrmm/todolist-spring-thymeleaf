package br.com.forgo.todolistforgo.service.userservice;

import br.com.forgo.todolistforgo.model.User;

import java.util.List;

public interface UserInterfaceService {

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    User findById(Long id);

    List<User> findAll();

    void deleteUser(Long id);
}
