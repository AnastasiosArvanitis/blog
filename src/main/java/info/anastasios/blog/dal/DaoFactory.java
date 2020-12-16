package info.anastasios.blog.dal;

import info.anastasios.blog.dal.dao.MemberDao;
import info.anastasios.blog.dal.dao.PostDao;

public class DaoFactory {

    public static PostDao getPostDao() {
        return new PostDaoJdbcImpl();
    }

    public static MemberDao getMemberDao() {
        return new MemberDaoJdbcImpl();
    }

}
