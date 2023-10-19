package applications.user.application;
import applications.user.User;
import applications.user.service.UserService;
public class UserApplication {
    UserService userService =  new UserService();
    public User createUser(User user) {return userService.createUser(user);}
}
