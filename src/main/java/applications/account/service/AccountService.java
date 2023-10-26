package applications.account.service;

import applications.account.Account;
import applications.account.repository.IAccountRepository;
import applications.account.repository.AccountRepository;
import applications.category.Category;

import java.util.List;

public class AccountService {
    IAccountRepository iAccountRepository = new AccountRepository();

    public Account createAccount(Account account) {
        return iAccountRepository.createAccount(account);
    }

    public Account getUserByAccount(String accountName) {
        return iAccountRepository.getByUsername(accountName);
    }
    public Long countUsername(String username) {
        return iAccountRepository.countUsername(username);
    }
    public List<Account> getAll() {
        return iAccountRepository.getAll();
    }
}
