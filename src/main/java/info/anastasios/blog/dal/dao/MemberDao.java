package info.anastasios.blog.dal.dao;

import info.anastasios.blog.bo.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {

    List<Member> listAllMembers() throws SQLException;

    Member selectLogin(String email, String password) throws SQLException;

    Member selectMemberById(int memberId) throws SQLException;

    Member insertMember(Member member) throws SQLException;

    boolean updateMember(Member member) throws SQLException;

    boolean deleteMember(Member member) throws SQLException;

}
