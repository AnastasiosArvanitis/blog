package info.anastasios.blog.servlets;

import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "Logout")
public class Logout extends HttpServlet {

    private Logger logger = BlogLogger.getLogger("Logout");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("member");
            session.invalidate();
            response.sendRedirect("/blog/");
        } catch (Exception e) {
            logger.severe("Error servlet Logout " + e.getMessage() + "\n");
            e.printStackTrace();
        }

    }
}
