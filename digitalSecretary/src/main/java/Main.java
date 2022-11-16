import data_base.DataManager;
import data_base.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //User.deleteUser(2);
        //User.selectAll("users");
        //User.addUser(new User("asdasd","asdasd"));
       // User.updateUser(1,new User("WFWEF","1234"));
        User.searchUser(new User("WFWEF","1234"));
    }
}
