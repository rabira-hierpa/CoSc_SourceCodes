package Events;

import Models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * IntraChat
 *
 * @ 02 13
 * Created by rabira on 12/19/16.
 */
public class SignUpController implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML
    private PasswordField pass;
    @FXML
    private VBox mainPane;
    @FXML
    private TextField userName;
    @FXML
    private TextField idfield;
    @FXML
    private TextField email;
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

    public void signup(ActionEvent e) {
        try {
            if (loginModel.checkUser(userName.getText(), pass.getText())) {
                dbstatus.setText("Correct username and password!");
            } else {
                dbstatus.setText("INCORRECT Username and Password");
            }
        } catch (SQLException e1) {
            dbstatus.setText("INCORRECT Username and Password");
            e1.printStackTrace();
        }
    }
}
