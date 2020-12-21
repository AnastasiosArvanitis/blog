package info.anastasios.blog.bll;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;
import info.anastasios.blog.dal.DaoFactory;
import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.dao.PostDao;
import info.anastasios.blog.utlis.BlogLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostManager {

    private static PostManager instance;
    private MemberDao memberDao;
    private PostDao postDao;

    private Logger logger = BlogLogger.getLogger("PostManager");

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
            logger.severe("Error method getPosts " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return listAllPosts;
    }

    public List<Post> getPostByMemberId(int memberId) throws SQLException {
        List<Post> listAllPosts = new ArrayList<>();
        try {
            listAllPosts = postDao.selectPostByMemberId(memberId);
        } catch (SQLException e) {
            logger.severe("Error method getPostByMemberId " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return listAllPosts;
    }

    public Post putPost(Post post) throws SQLException {
        Post tempPost = null;
        try {
            tempPost = postDao.insertPost(post);
        } catch (SQLException e) {
            logger.severe("Error method putPost " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return tempPost;
    }

    public boolean deletePost(int postId) throws SQLException {
        boolean result = false;
        try {
            result = postDao.deletePost(postId);
        } catch (SQLException e) {
            logger.severe("Error method deletePost " + e.getMessage() + "\n");
            e.printStackTrace();
        }
        return result;
    }

}
















