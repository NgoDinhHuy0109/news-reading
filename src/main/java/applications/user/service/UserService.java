package applications.user.service;
import applications.user.User;
import applications.user.repository.IUserRepository;
import applications.user.repository.UserRepository;
public class UserService {
    IUserRepository iUserRepository = new UserRepository();
    public User createUser(User user) {
        return iUserRepository.createUser(user);
    }
}
