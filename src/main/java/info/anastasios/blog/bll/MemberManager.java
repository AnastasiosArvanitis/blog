package info.anastasios.blog.bll;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.dal.DaoFactory;
import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.dao.PostDao;
import info.anastasios.blog.utlis.BlogLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MemberManager {

    private static MemberManager instance;
    private MemberDao memberDao;
    private PostDao postDao;

    private Logger logger = BlogLogger.getLogger("MemberManager");

    //Constructeur privé => PATTERN SINGLETON
    private MemberManager() {
        memberDao = DaoFactory.getMemberDao();
        postDao = DaoFactory.getPostDao();
    }

    //Permet de récupérer l'instance (créee une seule fois)
    public static MemberManager getInstance() {
        if (instance == null) {
            return new MemberManager();
        }
        return instance;
    }

    public Member getMemberById(int id) throws SQLException {

        Member member = null;
        try {
            member = memberDao.selectMemberById(id);
        } catch (SQLException e) {
            logger.severe("Error method getMemberById " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        return member;
    }

    public List<Member> getMembers() throws SQLException {
        List<Member> listAllMembers = new ArrayList<>();
        try {
             listAllMembers = memberDao.listAllMembers();
        } catch (SQLException e) {
            logger.severe("Error method getMembers " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return listAllMembers;
    }

    public Member getMemberLogin(String email, String password) throws SQLException {
        Member member = null;

        try {
            member = memberDao.selectLogin(email, password);
        } catch (SQLException e) {
            logger.severe("Error method getMemberLogin " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return member;
    }

    public Member putMember(Member member) throws SQLException {
        Member newMember = null;
        try {
            newMember = memberDao.insertMember(member);
        } catch (SQLException e) {
            logger.severe("Error method putMember " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return newMember;
    }

    public boolean editMember(Member member) throws SQLException {
        boolean updated = false;

        if (member.getFirstName().trim().equals("") || member.getLastName().trim().equals("") ||
            member.getEmail().trim().equals("") || member.getPassword().trim().equals("")) {
            throw new SQLException("Empty fields not allowed for update member");
        }

        try {
            updated = memberDao.updateMember(member);
        } catch (SQLException e) {
            logger.severe("Error method editMember " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return updated;
    }


}











