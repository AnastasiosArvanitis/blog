package info.anastasios.blog.bll;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.dal.DaoFactory;
import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.dao.PostDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostManager {

    private static PostManager instance;
    private MemberDao memberDao;
    private PostDao postDao;

    //Constructeur privé => PATTERN SINGLETON
    private PostManager() {
        memberDao = DaoFactory.getMemberDao();
        postDao = DaoFactory.getPostDao();
    }

    //Permet de récupérer l'instance (créee une seule fois)
    public static PostManager getInstance() {
        if (instance == null) {
            return new PostManager();
        }
        return instance;
    }

    public List<Post> getPosts() throws SQLException {
        List<Post> listAllPosts = new ArrayList<>();
        try {
            listAllPosts = postDao.listAllPosts();
        } catch (SQLException e) {
            System.out.println("BLL List all members error...");
            e.printStackTrace();
        }
        return listAllPosts;
    }

    public List<Post> getPostByMemberId(int memberId) throws SQLException {
        List<Post> listAllPosts = new ArrayList<>();
        try {
            listAllPosts = postDao.selectPostByMemberId(memberId);
        } catch (SQLException e) {
            System.out.println("BLL List all members error...");
            e.printStackTrace();
        }
        return listAllPosts;
    }

    public Post putPost(Post post) throws SQLException {
        Post tempPost = null;
        try {
            tempPost = postDao.insertPost(post);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tempPost;
    }

}
















