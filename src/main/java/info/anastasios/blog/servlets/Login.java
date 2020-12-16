package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.dal.MemberDaoJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Member member = new Member();
        try {
            member = memberManager.getMemberLogin(email, password);
            System.out.println(member.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (member != null) {
            HttpSession session = req.getSession();
            session.setAttribute("member", member);
            resp.sendRedirect("/blog/Posts");
        } else {
            resp.sendRedirect("/blog/UserNotFound.jsp");
        }
    }
}
