package applications.user.repository;

import applications.account.Account;
import applications.user.User;
public interface IUserRepository {
    User createUser(User user);

    User getUserById(String idUser);
}
