package Events;

import Models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * IntraChat
 *
 * @ 23 34
 * Created by rabira on 12/17/16.
 */
public class LoginValidation implements Initializable {

    public LoginModel loginModel = new LoginModel();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label dbstatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDBConnected()) {
            dbstatus.setText("DB Connected!");
        } else {
            dbstatus.setText("DB NOT Connected");
        }
    }

    public void Login(ActionEvent e) {
        try {
            if (loginModel.checkUser(username.getText(), password.getText())) {
                dbstatus.setText("Correct username and password!");
            } else {
                dbstatus.setText("INCORRECT Username and Password");
            }
        } catch (SQLException e1) {
            dbstatus.setText("INCORRECT Username and Password");
            e1.printStackTrace();
        }
    }

    public void Signup() {
        new SignUpController();
    }
}
