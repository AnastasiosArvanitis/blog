package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class SignUp extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

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
        Member signedUpMember = new Member();
        try {
            signedUpMember = memberManager.putMember(newMember);
            System.out.println(newMember.toString());
            System.out.println(signedUpMember.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (signedUpMember != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", signedUpMember);
            response.sendRedirect("/blog/Posts");
        } else {
            response.sendRedirect("/blog/UserNotFound.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.jsp");
    }
}






