package applications.authentication.application;

import applications.account.Account;
import applications.account.service.AccountService;
import applications.user.User;
import applications.user.service.UserService;
import org.apache.commons.lang3.StringUtils;

public class SignInApplication {
    UserService userService = new UserService();
    AccountService accountService = new AccountService();

    public User signIn(String username, String password) throws Exception {
        if (StringUtils.isEmpty(username)) {
            throw new Exception("Username can not be not empty");
        }
        if (StringUtils.isEmpty(password)) {
            throw new Exception("Password can not be not empty");
        }
        Account account = accountService.getUserByAccount(username);
        if (account == null || StringUtils.isEmpty(account.getUsername())) {
            throw new Exception("Account does not exist");
        }

        if (!account.getPassword().equals(password)) {
            throw new Exception("Password does not right");
        }

        return userService.getUserById(account.getUser_id());
    }
}
