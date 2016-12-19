package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass extends Application {
    private static Stage primary;

    public static void main(String args[]) {
        Application.launch(args);
    }

    public static Stage getPrimaryStage() {
        return primary;
    }

    public static void setPrimaryStage(Stage s) {
        primary = s;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            URL fxmlUrl = getClass().getResource("/Layouts/LoginUI.fxml");
            AnchorPane page = FXMLLoader.<AnchorPane>load(fxmlUrl);
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            setPrimaryStage(primaryStage);
            primaryStage.setTitle("IntraChat");
            primaryStage.show();

        } catch (Exception ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
