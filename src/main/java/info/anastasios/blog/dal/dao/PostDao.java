package info.anastasios.blog.dal.dao;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDao {

    List<Post> listAllPosts() throws SQLException;

    Post selectPostByTitle(String title) throws SQLException;

    List<Post> selectPostByMemberId(int memberId) throws SQLException;

    Post selectPostById(int postId) throws SQLException;

    Post insertPost(Post post) throws SQLException;

    boolean updatePost(Post post) throws SQLException;

    boolean deletePost(Post post) throws SQLException;


}
