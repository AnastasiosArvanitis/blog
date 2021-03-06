package info.anastasios.blog.servlets;

import com.google.gson.Gson;
import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class GetJsonPosts extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    private Logger logger = BlogLogger.getLogger("GetJsonPosts");

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
        List<Post> listPost = null;
        try {
            listPost = postManager.getPosts();
        } catch (SQLException e) {
            logger.severe("Error servlet GetJsonPosts " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        String json = new Gson().toJson(listPost);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
