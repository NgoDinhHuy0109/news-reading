package applications.authentication.controller;

import applications.account.Account;

import applications.authentication.application.RegisterApplication;
import applications.user.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "RegisterServletController", urlPatterns = {"/sign_up"})
public class RegisterServletController extends HttpServlet {
    RegisterApplication registerApplication = new RegisterApplication();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/sign_up/sign_up.html";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("join")) {
            url = "/sign_up/sign_up.html";    // the "join" page
        }
        if (action.equals("add")) {
            String userName = request.getParameter("username");
            String email = request.getParameter("email");
            String description = request.getParameter("description");
            String username = request.getParameter("account");
            String password = request.getParameter("password");
            User user = User.builder()
                    .userName(userName)
                    .email(email)
                    .description(description)
                    .build();
            Account account1 = Account.builder()
                    .username(username)
                    .password(password)
                    .build();
            try {
                registerApplication.register(user, account1);
            } catch (Exception e) {
                User user1 = new User();
                user1.setUserName(userName);
                user1.setEmail(email);
                user1.setDescription(description);
                Account users = new Account();
                users.setUsername(username);
                users.setPassword(password);
                request.setAttribute("users", users);
                request.setAttribute("user1", user1);
                url = "/sign_up/error_notification.jsp";
                request.setAttribute("error",e.getMessage());

                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
            }

            request.setAttribute("user", user);

            url = "/user/user.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
