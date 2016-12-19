package Events;


import Application.MainClass;
import Models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * IntraChat
 *
 * @ 23 34
 * Created by rabira on 12/17/16.
 */
public class LoginController implements Initializable {

    public LoginModel loginModel = new LoginModel();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn, signupBtn;
    @FXML
    private Label dbstatus;

    public static void setUI(URL path) {
        try {
            Pane page;
            Stage primary = MainClass.getPrimaryStage();
            FXMLLoader loader = new FXMLLoader();
            assert path != null;
            page = loader.load(path.openStream());
            Scene scene = new Scene(page);
            primary.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            } else if (username.getText().equals("") || password.getText().equals("")) {
                dbstatus.setText("Both fields are required");
            } else {
                dbstatus.setText("INCORRECT Username and Password");
            }
        } catch (SQLException e1) {
            dbstatus.setText("INCORRECT Username and Password");
            e1.printStackTrace();
        }
    }

    public void Signup() {
        try {
            if (!(loginModel.checkUser(username.getText(), password.getText()))) {
                URL signupxml = getClass().getResource("/Layouts/SignupUI.fxml");
                Stage primary = MainClass.getPrimaryStage();
                FXMLLoader loader = new FXMLLoader();
                assert signupxml != null;
                Pane signupPage = loader.load(signupxml.openStream());
                Scene scene = new Scene(signupPage);
                primary.setScene(scene);
            } else if (username.getText().equals("") || password.getText().equals("")) {
                URL signupxml = getClass().getResource("/Layouts/SignupUI.fxml");
                setUI(signupxml);
            } else {
                dbstatus.setText("User Already Exists!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
//        new SignUpController(MainClass.getPrimaryStage());
    }
}
