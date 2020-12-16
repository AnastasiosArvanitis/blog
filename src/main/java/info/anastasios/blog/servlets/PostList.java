package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.dal.PostDaoJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PostList extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Post> listPost = null;
        try {
            listPost = postManager.getPosts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listPost", listPost);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/PostList.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
