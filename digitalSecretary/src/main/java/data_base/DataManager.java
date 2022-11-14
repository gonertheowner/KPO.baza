package data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {
    public static final Connection connection;

    static
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            connection = DriverManager.getConnection(ConfProperties.getProperty("db.URL"), ConfProperties.getProperty("db.login"), ConfProperties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectAll(String tableName) throws SQLException {
        if (tableName == null) {
            // trow some exception
        }
        var statement = connection.prepareStatement("SELECT * FROM " + tableName);
        if (statement.execute()) {
            System.out.println("Success!");
        } else {
            // throw some exception
        }
    }
}
