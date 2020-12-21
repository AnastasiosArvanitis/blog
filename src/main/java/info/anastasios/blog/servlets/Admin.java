package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class Admin extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    private Logger logger = BlogLogger.getLogger("Admin");

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> listPosts = null;
        List<Member> listMembers = null;
        try {
            listPosts = postManager.getPosts();
            listMembers = memberManager.getMembers();
        } catch (SQLException e) {
            logger.severe("Error servlet Admin " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        if (listPosts != null && listMembers != null) {
            request.setAttribute("listPosts", listPosts);
            request.setAttribute("listMembers", listMembers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Admin.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/blog/Error?error=adminError");
        }

    }
}

















