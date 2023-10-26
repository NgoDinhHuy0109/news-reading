package applications.authentication.application;

import applications.account.Account;
import applications.account.service.AccountService;
import applications.user.User;
import applications.user.service.UserService;
import org.apache.commons.lang3.StringUtils;

public class RegisterApplication {
    UserService userService = new UserService();
    AccountService accountService = new AccountService();

    public User register(User user, Account account) throws Exception {
        User newUser = userService.createUser(user);
        if (StringUtils.isEmpty(account.getUsername())) {
            throw new Exception("User name cannot be not empty");
        }
        if(account.getUsername().matches(".*\\s+.*")) {
            throw new Exception("User name cannot contain whitespace");
        }
        if(accountService.countUsername(account.getUsername()) >= 1) {
            throw new Exception("User name existed ");
        }
        String user_id = newUser.get_id();
        account.setUser_id(user_id);
        Account newAccount = accountService.createAccount(account);
        return newUser;
    }


}
