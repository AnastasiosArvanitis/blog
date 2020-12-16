package info.anastasios.blog.dal;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.jdbcTools.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoJdbcImpl implements MemberDao {

    private Connection connection = null;

    @Override
    public List<Member> listAllMembers() throws SQLException {
        List<Member> listMember = new ArrayList<>();
        String sql = "select * from Members";

        try {
            connection = ConnectionManager.connect();
            Statement query =  connection.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                listMember.add(memberBuilder(resultSet));
            }
            query.close();
            resultSet.close();
            ConnectionManager.disconnect();
        } catch (Exception e) {
            System.out.println("Cant connect to database... ");
            e.printStackTrace();
        }
        return listMember;
    }

    @Override
    public Member selectLogin(String email, String password) throws SQLException {
        Member member = new Member();
        String sql = "select * from Members where email=? and password=?";

        try {
            connection = ConnectionManager.connect();
            PreparedStatement query = connection.prepareStatement(sql);
            query.setString(1, email);
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                member = memberBuilder(resultSet);
            }
            resultSet.close();
            query.close();
            ConnectionManager.disconnect();
        } catch (Exception e) {
            System.out.println("Cant connect to database... ");
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Member selectMemberById(int memberId) throws SQLException {
        Member member = new Member();
        String sql = "select * from Members where memberId=?";
        try {
            connection = ConnectionManager.connect();
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, memberId);
            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                member = memberBuilder(resultSet);
            }
            resultSet.close();
            query.close();
            ConnectionManager.disconnect();
        } catch (Exception e) {
            System.out.println("Cant connect to database... ");
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Member insertMember(Member member) throws SQLException {
        int totalOfLines = 0;
        String sqlInsert = "insert into Members (firstName, lastName, email, password) values (?,?,?,?)";
        String sqlSelect = "select * from Members where memberId=?";
        try {
            connection = ConnectionManager.connect();
            PreparedStatement insert = connection.prepareStatement(sqlInsert);
            insert.setString(1, member.getFirstName());
            insert.setString(2, member.getLastName());
            insert.setString(3, member.getEmail());
            insert.setString(4, member.getPassword());
            totalOfLines = insert.executeUpdate();
           if (totalOfLines > 0) {
               PreparedStatement select = connection.prepareStatement(sqlSelect);
               select.setInt(1, member.getId());
               ResultSet resultSet = select.executeQuery();

               if (resultSet.next()) {
                   member.setId(resultSet.getInt(1));
               }
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return member;
    }

    @Override
    public boolean updateMember(Member member) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteMember(Member member) throws SQLException {
        return false;
    }


    private Member memberBuilder(ResultSet rs) throws SQLException {

        Member member = new Member();
        member.setId(rs.getInt("memberId"));
        member.setFirstName( rs.getString("firstName"));
        member.setLastName(rs.getString("lastName"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("password"));

        return member;
    }

}




















