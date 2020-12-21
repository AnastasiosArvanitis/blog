package info.anastasios.blog.servlets;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.dal.MemberDaoJdbcImpl;
import info.anastasios.blog.utlis.BlogLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class MemberList extends HttpServlet {

    private MemberDaoJdbcImpl memberDao;

    private Logger logger = BlogLogger.getLogger("MemberList");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Member> listMember = null;
        try {
            listMember = memberDao.listAllMembers();
        } catch (SQLException e) {
            logger.severe("Error servlet MemberList " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        request.setAttribute("listMember", listMember);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MemberList.jsp");
        dispatcher.forward(request, response);

    }

}
