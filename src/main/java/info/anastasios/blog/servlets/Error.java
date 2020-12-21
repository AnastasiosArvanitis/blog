package info.anastasios.blog.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Error extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorParametere = request.getParameter("error");
        String errorMessage = "";

        switch (errorParametere) {
            case "userNotFound":
                errorMessage = "The user was not found, please try again";
                break;
            case "notLoggedIn":
                errorMessage = "You are not logged in, please try again";
                break;
            case "notValidInput":
                errorMessage = "Not a valid email or password, please try again";
                break;
            case "notValidSignupInput":
                errorMessage = "Not a valid name or email or password, please try again";
                break;
            case "signUpFailed":
                errorMessage = "The sign up procces failed, please try again";
                break;
            case "addPostFailed":
                errorMessage = "Adding a new post failed, please try again";
                break;
            case "deletePostFailed":
                errorMessage = "Deleting your post failed, please try again";
                break;
            case "editPostFailed":
                errorMessage = "Editing your post failed, please try again";
                break;
            case "editProfile":
                errorMessage = "Editing your profile failed, please try again";
                break;
            default:
                errorMessage = "The page that you are looking for was not found, please try again";
                break;
        }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Error.jsp?error=" + errorMessage);
            dispatcher.forward(request, response);

    }
}
