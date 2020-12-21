package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DeletePost extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    private Logger logger = BlogLogger.getLogger("DeletePost");

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("postID"));
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        boolean isAdmin = member.getIsAdmin();
        boolean deletedPost = false;
        try {
            deletedPost = postManager.deletePost(postId);
        } catch (SQLException e) {
            logger.severe("Error servlet DeletePost " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        if (deletedPost) {
            if (!isAdmin) {
                response.sendRedirect("/blog/MyProfil");
            } else {
                response.sendRedirect("/blog/Admin");
            }
        } else {
            response.sendRedirect("/blog/Error?error=deletePostFailed");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
