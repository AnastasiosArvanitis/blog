package info.anastasios.blog.bll;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.dal.DaoFactory;
import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.dao.PostDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    private static MemberManager instance;
    private MemberDao memberDao;
    private PostDao postDao;

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
            System.out.println("error bll member manager");
            e.printStackTrace();
        }

        return member;
    }

    public List<Member> getMembers() throws SQLException {
        List<Member> listAllMembers = new ArrayList<>();
        try {
             listAllMembers = memberDao.listAllMembers();
        } catch (SQLException e) {
            System.out.println("BLL List all members error...");
            e.printStackTrace();
        }
        return listAllMembers;
    }

    public Member getMemberLogin(String email, String password) throws SQLException {
        Member member = null;

        try {
            member = memberDao.selectLogin(email, password);
        } catch (SQLException e) {
            System.out.println("BLL member loggin error...");
            e.printStackTrace();
        }
        return member;
    }

    public Member putMember(Member member) throws SQLException {
        Member tempMember = null;
        try {
            tempMember = memberDao.insertMember(member);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tempMember;
    }

}
