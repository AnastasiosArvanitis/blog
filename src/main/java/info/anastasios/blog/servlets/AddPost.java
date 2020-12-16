package info.anastasios.blog.servlets;

import info.anastasios.blog.bll.MemberManager;
import info.anastasios.blog.bll.PostManager;
import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class AddPost extends HttpServlet {

    private MemberManager memberManager = null;
    private PostManager postManager = null;
    private Member thisMember = null;

    @Override
    public void init() throws ServletException {
        super.init();
        memberManager = MemberManager.getInstance();
        postManager = PostManager.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //LocalDate date = LocalDate.now();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);

        HttpSession session = request.getSession(false);
        thisMember = (Member) session.getAttribute("member");

        String title = request.getParameter("title");
        String postBody = request.getParameter("postBody");

        Post newPost = new Post(title, postBody, strDate, thisMember);
        Post insertedPost = new Post();
        try {
            insertedPost = postManager.putPost(newPost);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (insertedPost != null) {
            response.sendRedirect("/blog/MyProfil");
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/Profil");
            //dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/blog/UserNotFound.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
