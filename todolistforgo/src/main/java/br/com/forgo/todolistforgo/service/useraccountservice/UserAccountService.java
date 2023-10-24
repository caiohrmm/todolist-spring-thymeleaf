package br.com.forgo.todolistforgo.service.useraccountservice;

import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.model.UserAccount;
import br.com.forgo.todolistforgo.repository.UserAccountRepository;
import br.com.forgo.todolistforgo.security.UserAccountDetails;
import br.com.forgo.todolistforgo.service.roleservice.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserAccountService implements
        UserDetailsService, UserAccountInterfaceService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleServiceImpl roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository
                .findUserAccountByUsername(username)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new UsernameNotFoundException("username not found")
                );
        return new UserAccountDetails(userAccount);
    }

    @Override
    public UserAccount createUserAccount(User user) {
        UserAccount userAccount = new UserAccount(user.getEmail(),
                passwordEncoder.encode(user.getPassword()))
                .addRoles(Collections.singleton(roleService.getRoleUSER()))
                .addUser(user);
        Optional<UserAccount> userAccountByUsername = userAccountRepository
                .findUserAccountByUsername(userAccount.getUsername());
        if (userAccountByUsername.isEmpty()){
            return userAccountRepository.save(userAccount);
        }
        return userAccountByUsername.stream()
                .findFirst()
                .orElseThrow(() -> new
                        RuntimeException("User account not found"));
    }
}
