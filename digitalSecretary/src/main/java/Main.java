import data_base.DataManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");
        DataManager.selectAll("users");
    }
}
