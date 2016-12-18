package Models;

import UserData.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * IntraChat
 *
 * @ 02 24
 * Created by rabira on 12/19/16.
 */
public class SignUpModal {
    Connection connection;

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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "insert into users(username,password) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to insert record");
            return false;
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
        return false;
    }
}
