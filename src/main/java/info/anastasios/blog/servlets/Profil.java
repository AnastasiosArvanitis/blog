package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class Profil extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;
    private Member member = null;

    private Logger logger = BlogLogger.getLogger("Profil");

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        member = (Member) session.getAttribute("member");

        List<Post> listPost = null;
        try {
            listPost = postManager.getPostByMemberId(member.getId());

        } catch (SQLException e) {
            logger.severe("Error servlet Profil " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        if (member != null) {
            request.setAttribute("memberListPost", listPost);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Profil.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/blog/Error?error=notLoggedIn");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
