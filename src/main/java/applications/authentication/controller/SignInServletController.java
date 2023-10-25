package applications.authentication.controller;


import applications.authentication.application.SignInApplication;
import applications.user.User;
import applications.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInServletController", urlPatterns = {"/sign_in"})
public class SignInServletController extends HttpServlet {
    SignInApplication signInApplication = new SignInApplication();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/sign_in/sign_in.html";
        UserService userService = new UserService();
        String action = request.getParameter("action");
        if (action == null) {
            action = "submit";  // default action
        }

        if (action.equals("submit")) {
            url = "/sign_in/sign_in.html";    // the "join" page
        }
        if (action.equals("add")) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                signInApplication.signIn(userName, password);

            } catch (Exception e) {
                url = "/sign_in/error_notification.jsp";
                request.setAttribute("error",e.getMessage());
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
            }

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
