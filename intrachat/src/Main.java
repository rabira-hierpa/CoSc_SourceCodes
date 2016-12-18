import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    public static void main(String args[]) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            URL fxmlUrl = getClass().getResource("Layouts/LoginUI.fxml");

            AnchorPane page = FXMLLoader.<AnchorPane>load(fxmlUrl);
            Scene scene = new Scene(page);

            primaryStage.setScene(scene);
            primaryStage.setTitle("IntraChat");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
