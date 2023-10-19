package applications.category.controller;

import applications.category.Category;
import applications.category.application.CategoryApplication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServletController", urlPatterns = {"/category"})
public class CategoryServletController extends HttpServlet {

    CategoryApplication categoryApplication = new CategoryApplication();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        categoryApplication.getAllCategories();
        String url = "/category/category.html";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("join")) {
            url = "/category/category.html";    // the "join" page
        }
        if (action.equals("add")) {
            String categoryName = request.getParameter("categoryName");
            String description = request.getParameter("description");

            Category category = Category.builder()
                    .categoryName(categoryName)
                    .description(description)
                    .build();
            try {
                categoryApplication.createCategory(category);
            } catch (Exception e) {

                url = "/category/error_notification.jsp";
                request.setAttribute("error",e.getMessage());

                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
            }

            request.setAttribute("category", category);

            url = "/category/thanks.jsp";
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
