package applications.authentication.application;

import applications.account.Account;
import applications.account.service.AccountService;
import applications.user.User;
import applications.user.service.UserService;
import org.apache.commons.lang3.StringUtils;

public class RegisterApplication {
    UserService userService = new UserService();
    AccountService accountService = new AccountService();

    public User register(User user, Account account) {
        User newUser = userService.createUser(user);
        String user_id = newUser.get_id();
        account.setUser_id(user_id);
        Account newAccount = accountService.createAccount(account);
        return newUser;
    }


}
