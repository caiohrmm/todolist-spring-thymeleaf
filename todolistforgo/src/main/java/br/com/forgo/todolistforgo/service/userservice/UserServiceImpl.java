package br.com.forgo.todolistforgo.service.userservice;

import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInterfaceService {

    private final UserRepository repository;

    @Autowired
    private UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        if (user.getId() == null) return repository.save(user);
        return null;
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser == null) throw new IllegalArgumentException("Usuário não encontrado com o ID fornecido.");
        ;
        // Verifique se os campos não estão vazios antes de atualizar
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPhone() != null) {
            existingUser.setPhone(updatedUser.getPhone());
        }

        return repository.save(existingUser);
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = repository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID fornecido.");
        }
        ;
        repository.deleteById(id);

    }
}
