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
@WebServlet(name = "RegisterServletController", urlPatterns = {"/register"})
public class RegisterServletController extends HttpServlet {
    RegisterApplication registerApplication = new RegisterApplication();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/register/register.html";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("join")) {
            url = "/register/register.html";    // the "join" page
        }
        if (action.equals("add")) {
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String description = request.getParameter("description");
            String username = request.getParameter("username");
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
            registerApplication.register(user, account1);

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
