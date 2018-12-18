package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection conn = null;

    // Note: no public/private/protected modifier = "package private" - i.e.,
    // accessible only to classes in the same package.
    // So, the MonsterAccessor can call this method, but the servlets can't.
    static Connection getConnection() throws SQLException {

        return getConnection("jdbc:mysql://localhost:3306/squair_dev", "root", "");

    }

    static Connection getConnection(String url, String user, String password) throws SQLException {
        if (conn == null) {

            conn = DriverManager.getConnection(url, user, password);

        }
        return conn;
    }

} // end class ConnectionManager
