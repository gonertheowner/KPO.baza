package data_base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private int id;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void addUser(User user) {
        try {
            PreparedStatement preparedStatement = DataManager.connection.prepareStatement("Insert Into users (login, password) VALUES (?,?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // OurClassException
            throw new RuntimeException(e);
        }
    }

    public static boolean updateUser(int index, User user) {
        boolean flag;
        try {
            PreparedStatement preparedStatement = DataManager.connection.prepareStatement("UPDATE users SET login=?, password=? WHERE ID=?");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, index);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public static boolean searchUser(User user) {
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = DataManager.connection.prepareStatement("SELECT login FROM users WHERE EXISTS(SELECT login FROM users where login=?)");
            preparedStatement.setString(1, user.getLogin());
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            // OurClassException
            throw new RuntimeException(e);
        }
        return flag;
    }
}
