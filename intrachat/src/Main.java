import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane rootPane = new StackPane();
        Button testButton = new Button("Hello");
        Scene mainScene = new Scene(rootPane, 400, 400);
        testButton.setOnAction(e -> primaryStage.close());
        rootPane.getChildren().add(testButton);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Hello Intra Chat!");
        primaryStage.show();
    }
}
