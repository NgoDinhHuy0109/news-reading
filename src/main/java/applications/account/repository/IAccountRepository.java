package applications.account.repository;

import applications.account.Account;
import applications.user.User;

import java.util.List;

public interface IAccountRepository {
    Account createAccount(Account account);

    Account getByUsername(String accountName);

    Long countUsername(String username);

    List<Account> getAll();
}
