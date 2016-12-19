package Models;

import UserData.SqliteConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * IntraChat
 *
 * @ 02 24
 * Created by rabira on 12/19/16.
 */
public class SignUpModal {
    Connection connection;
    LoginModel loginModel = new LoginModel();
    public SignUpModal() {
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.out.println("Connection to db failed!");
            System.exit(1);
        }
    }

    public boolean isDBConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertNewUser(String name, String password) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "insert into users(username,password) values (?,?)";
        try {
            if (isDBConnected()) {
                if (loginModel.checkUser(name, password)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "User already exits", ButtonType.CLOSE);
                    alert.show();
                    return false;
                }
                if (!name.equals(password)) {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, password);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Username and password must be Different!", ButtonType.CLOSE);
                    alert.show();
                    return false;
                }
            } else {
                System.out.println("Unable to connect to db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to insert record");
            return false;
        }
        return true;
    }
}
