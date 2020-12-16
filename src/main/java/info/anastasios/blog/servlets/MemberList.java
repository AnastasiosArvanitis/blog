package info.anastasios.blog.servlets;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.dal.MemberDaoJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MemberList extends HttpServlet {

    private MemberDaoJdbcImpl memberDao;
    //private PostDao postDao;



    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Member> listMember = null;
        try {
            listMember = memberDao.listAllMembers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listMember", listMember);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MemberList.jsp");
        dispatcher.forward(request, response);

    }

}
