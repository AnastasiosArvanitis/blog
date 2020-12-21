package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;


public class Login extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    private Logger logger = BlogLogger.getLogger("Login");

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Member member = null;

        if (ValidateInput.validateEmail(email) && !password.trim().equals("")) {
            try {
                member = memberManager.getMemberLogin(email, password);
                if (Objects.isNull(member)) {
                    response.sendRedirect("/blog/Error?error=userNotFound");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("member", member);
                    response.sendRedirect("/blog/Posts");
                }

            } catch (SQLException e) {
                logger.severe("Error servlet Login " + e.getMessage() + "\n");
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/blog/Error?error=notValidInput");
        }
    }
}
