

import java.sql.*;

/**
 * Created by user on 05.03.2016.
 */
public class DBWork {

    private static final String URL = "jdbc:mysql://localhost:3306/mybdlaba";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    public DBWork(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
            // «акрываем ресурсы, не допуска€ их утечки.
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
