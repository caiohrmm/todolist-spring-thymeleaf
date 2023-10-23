package br.com.forgo.todolistforgo.service.useraccountservice;

import br.com.forgo.todolistforgo.model.User;
import br.com.forgo.todolistforgo.model.UserAccount;

public interface UserAccountInterfaceService {
    UserAccount createUserAccount(User user);
}
