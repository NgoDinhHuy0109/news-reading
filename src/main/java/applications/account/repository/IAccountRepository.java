package applications.account.repository;

import applications.account.Account;

import java.util.List;

public interface IAccountRepository {
    Account createAccount(Account account);


    Account getByUsername(String account);
}
