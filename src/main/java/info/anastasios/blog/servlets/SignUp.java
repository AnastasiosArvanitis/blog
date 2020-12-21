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
import java.util.logging.Logger;


public class SignUp extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    private Logger logger = BlogLogger.getLogger("SignUp");

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Member newMember = new Member(firstName, lastName, email, password);
        Member signedUpMember = null;

        if (ValidateInput.validateName(firstName) && ValidateInput.validateName(firstName) &&
                ValidateInput.validateEmail(email) && password.trim().length() < 2) {
            try {
                signedUpMember = memberManager.putMember(newMember);
            } catch (SQLException e) {
                logger.severe("Error servlet SignUp " + e.getMessage() + "\n");
                e.printStackTrace();
            }
            if (signedUpMember != null) {
                HttpSession session = request.getSession();
                session.setAttribute("member", signedUpMember);
                response.sendRedirect("/blog/Posts");
            } else {
                response.sendRedirect("/blog/Error?error=signUpFailed");
            }
        } else {
            response.sendRedirect("/blog/Error?error=notValidSignupInput");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/SignUpForm.jsp");
        dispatcher.forward(request, response);
    }
}






