import data_base.DataManager;
import data_base.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");
        //DataManager.deleteUser(2);
        //DataManager.selectAll("users");
        DataManager.addUser(new User("asdasd","asdasd"));

    }
}
