import data_base.Connector;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");
        Connector.selectAll();
    }
}
