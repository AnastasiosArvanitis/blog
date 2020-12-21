package info.anastasios.blog.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChoseTheme extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String colorTheme = request.getParameter("colorTheme");
        Cookie[] blogCookies = request.getCookies();

        if (blogCookies == null) {
            Cookie themeCookie = new Cookie("colorTheme",colorTheme);
            themeCookie.setMaxAge(60*60*24*7*365);
            response.addCookie(themeCookie);
        } else {
            for (Cookie tempCookie : blogCookies) {
                if (tempCookie.getName().equals("colorTheme")) {
                   tempCookie.setValue(colorTheme);
                } else {
                    Cookie themeCookie = new Cookie("colorTheme",colorTheme);
                    themeCookie.setMaxAge(60*60*24*7*365);
                    response.addCookie(themeCookie);
                }
                break;
            }
        }
        response.sendRedirect("/blog/MyProfil");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
