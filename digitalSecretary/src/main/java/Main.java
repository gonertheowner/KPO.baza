import data_base.DataManager;
import data_base.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //DataManager.deleteUser(2);
        //DataManager.selectAll("users");
        //DataManager.addUser(new User("asdasd","asdasd"));
       // DataManager.updateUser(1,new User("WFWEF","1234"));
        DataManager.searchUser(new User("WFWEF","1234"));
    }
}
