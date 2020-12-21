package info.anastasios.blog.dal.jdbcTools;

import info.anastasios.blog.utlis.BlogLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionManager {

    private static String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=OurBlog";
    private static String jdbcUserName = "sa";
    private static String jdbcUserPassword = "flox123";
    private static Connection jdbcConnection;

    private static Logger logger = BlogLogger.getLogger("ConnectionManager");

    public static Connection connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                logger.severe("Error method listAllMembers " + e.getMessage() + "\n");
                throw new SQLException(e);
            }
        }
        jdbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcUserPassword);
        return jdbcConnection;
    }

        public static void disconnect() throws SQLException {
            if (jdbcConnection != null && !jdbcConnection.isClosed()) {
                jdbcConnection.close();
            }
        }

}
