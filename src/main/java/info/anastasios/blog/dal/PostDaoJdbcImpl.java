package info.anastasios.blog.dal;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.dao.PostDao;
import info.anastasios.blog.dal.jdbcTools.ConnectionManager;
import info.anastasios.blog.utlis.BlogLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostDaoJdbcImpl implements PostDao {

    private Connection connection = null;

    private Logger logger = BlogLogger.getLogger("PostDaoJdbcImpl");

    @Override
    public List<Post> listAllPosts() throws SQLException {
        List<Post> listPost = new ArrayList<>();
        String sql = "select * from Posts order by postId desc";
        try {
            connection = ConnectionManager.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                listPost.add(postBuilder(resultSet));
            }
            resultSet.close();
            statement.close();
            ConnectionManager.disconnect();
        } catch (Exception e) {
            logger.severe("Error method listAllPosts " + e.getMessage() + "\n");
            e.printStackTrace();
        }

        return listPost;
    }

    @Override
    public Post selectPostByTitle(String title) throws SQLException {
        return null;
    }

    @Override
    public List<Post> selectPostByMemberId(int memberId) throws SQLException {
        List<Post> membersPosts = new ArrayList<>();
        String sql = "select * from Posts where member=? order by postId desc";
        try {
            connection = ConnectionManager.connect();
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, memberId);
            ResultSet resultSet = query.executeQuery();

            while(resultSet.next()) {
                membersPosts.add(postBuilder(resultSet));
            }
            query.close();
            resultSet.close();
            ConnectionManager.disconnect();
        } catch (SQLException e) {
            logger.severe("Error method selectPostByMemberId " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return membersPosts;
    }

    @Override
    public Post selectPostById(int postId) throws SQLException {
        return null;
    }

    @Override
    public Post insertPost(Post post) throws SQLException {
        int totalOfLines = 0;
        String sqlInsert = "insert into Posts (title, body, dateCreated, member) values (?,?,?,?)";
        String sqlSelect = "select * from Posts where postId=? order by postId desc";
        try {
            connection = ConnectionManager.connect();
            PreparedStatement insert = connection.prepareStatement(sqlInsert);
            insert.setString(1, post.getTitle());
            insert.setString(2, post.getBody());
            insert.setString(3, post.getDate());
            if (post.getMember() != null) {
                insert.setInt(4, post.getMember().getId());
            } else {
                insert.setNull(4, Types.INTEGER);
            }

            totalOfLines = insert.executeUpdate();
            if (totalOfLines > 0) {
                PreparedStatement select = connection.prepareStatement(sqlSelect);
                select.setInt(1, post.getPostId());
                ResultSet resultSet = select.executeQuery();

                if (resultSet.next()) {
                    post.setPostId(resultSet.getInt(1));
                }
                select.close();
                resultSet.close();
            }
            insert.close();
            ConnectionManager.disconnect();
        } catch (SQLException e) {
            logger.severe("Error method insertPost " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public boolean updatePost(Post post) throws SQLException {
        return false;
    }

    @Override
    public boolean deletePost(int postId) throws SQLException {
        String sqlDelete = "delete from Posts where postId=?";
        int totalLinesModified = 0;
        try  {
            connection = ConnectionManager.connect();
            PreparedStatement query = connection.prepareStatement(sqlDelete);
            query.setInt(1, postId);
            totalLinesModified = query.executeUpdate();
        } catch (SQLException ex) {
            logger.severe("Error method deletePost " + ex.getMessage() + "\n");
           ex.printStackTrace();
        }
        return totalLinesModified > 0;
    }

    private Post postBuilder(ResultSet rs) throws SQLException {

        Member memberPost = this.getMemberPost(rs.getInt("member"));
        Post post = new Post();
        post.setPostId(rs.getInt("postId"));
        post.setTitle( rs.getString("title"));
        post.setBody(rs.getString("body"));
        post.setDate(rs.getString("dateCreated"));
        post.setMember(memberPost);

        return post;
    }

    private Member getMemberPost(int memberId) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        Member memberPost = null;
        try {
            memberPost = memberDao.selectMemberById(memberId);
        } catch (SQLException e) {
            logger.severe("Error method getMemberPost " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return memberPost;
    }



}






















