package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private DBConnection () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/supermarket", "jeewa", "JeeWa$288fernando");
    }

    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null ? dbConnection = new DBConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
