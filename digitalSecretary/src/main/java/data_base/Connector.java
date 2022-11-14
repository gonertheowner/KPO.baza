package data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    static Properties property = new Properties();
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
            connection = DriverManager.getConnection(property.getProperty("db.URL"), property.getProperty("db.login"), property.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
