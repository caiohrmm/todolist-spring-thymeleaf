package br.com.forgo.todolistforgo.service.roleservice;

import br.com.forgo.todolistforgo.model.Authority;
import br.com.forgo.todolistforgo.model.Role;
import br.com.forgo.todolistforgo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleInterfaceService {

    @Autowired
    private RoleRepository repository;
    @Override
    public Role getRoleUSER() {
        Role role = new Role("USER")
                .addAuthorities(Set.of(
                        new Authority("user:read"),
                        new Authority("user:write")));
        Optional<Role> theRole = repository
                .findRoleByRoleName(role.getRoleName());
        if (theRole.isEmpty()){
            return repository.save(role);
        }
        return theRole.stream()
                .findFirst()
                .orElseThrow(() -> new
                        RuntimeException("role not found"));
    }
}
