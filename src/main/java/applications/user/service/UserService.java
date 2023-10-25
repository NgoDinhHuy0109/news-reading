package applications.user.service;
import applications.account.Account;
import applications.user.User;
import applications.user.repository.IUserRepository;
import applications.user.repository.UserRepository;
public class UserService {
    IUserRepository iUserRepository = new UserRepository();
    public User createUser(User user) {
        return iUserRepository.createUser(user);
    }
    public User getUserById(String userId) {return iUserRepository.getUserById(userId);}

}
