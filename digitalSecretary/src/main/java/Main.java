import data_base.DataManager;
import data_base.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");
        //DataManager.selectAll("users");
        //DataManager.addUser(new User(3,"asdasd","asdasd"));
        DataManager.deleteUser(1);
    }
}
