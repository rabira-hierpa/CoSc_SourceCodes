package Events;

import Application.MainClass;
import Models.LoginModel;
import Models.SignUpModal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TextField userName;
    @FXML
    private Label dbstatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!loginModel.isDBConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to Connect to Database", ButtonType.CLOSE);
            alert.show();
        }
    }
    public void signup(ActionEvent e) {
        try {
            SignUpModal insert = new SignUpModal();
            if (userName.getText().equals("") || pass.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User Name and Password Required", ButtonType.CLOSE);
                alert.show();
            } else {
                if (insert.insertNewUser(userName.getText(), pass.getText())) {
                    dbstatus.setText("Sign up successful!");
                } else {
                    dbstatus.setText("Unable to create account!");
                }
            }
        } catch (SQLException e1) {
            dbstatus.setText("INCORRECT Username and Password");
            e1.printStackTrace();
        }
    }

    public void goBack(ActionEvent e) throws IOException {
        Stage primary = MainClass.getPrimaryStage();
        FXMLLoader loader = new FXMLLoader();
        URL loginurl = getClass().getResource("/Layouts/LoginUI.fxml");
        Pane loginPage = FXMLLoader.load(loginurl);
        Scene loginScene = new Scene(loginPage);
        primary.setScene(loginScene);
    }
}
