package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UpdateMember extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    private Logger logger = BlogLogger.getLogger("UpdateMember");

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

        HttpSession session = request.getSession();
        Member currentMember = (Member) session.getAttribute("member");
        int memberId = currentMember.getId();

        boolean updated = false;
        Member updatedMember = new Member(memberId, firstName, lastName, email, password);
        try {
            updated = memberManager.editMember(updatedMember);
        } catch (SQLException e) {
            logger.severe("Error servlet UpdateMember " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        if (updated) {
            session.setAttribute("member", updatedMember);
            response.sendRedirect("/blog/MyProfil");
        } else {
            response.sendRedirect("/blog/Error?error=editProfileFailed");
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
