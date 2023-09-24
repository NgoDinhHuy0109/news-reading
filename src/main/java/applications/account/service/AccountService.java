package applications.account.service;
import applications.account.Account;
import applications.account.repository.IAccountRepository;
import applications.account.repository.AccountRepository;

public class AccountService {
    IAccountRepository iUserRepository = new AccountRepository();
    public Account createAccount(Account account) {
        return iUserRepository.createAccount(account);
    }
}
