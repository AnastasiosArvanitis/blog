package info.anastasios.blog.dal.jdbcTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=OurBlog";
    private static String jdbcUserName = "sa";
    private static String jdbcUserPassword = "flox123";
    private static Connection jdbcConnection;


    public static Connection connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }
        jdbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcUserPassword);
        //System.out.println("connecting to the database..." + jdbcUrl);
        return jdbcConnection;
    }

        public static void disconnect() throws SQLException {
            if (jdbcConnection != null && !jdbcConnection.isClosed()) {
                //System.out.println("disconnecting from the database..." + jdbcUrl);
                jdbcConnection.close();
            }
        }

}
