package start;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.*;
import javafx.scene.*;

public class Start extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Resources/startLayout.fxml"));
        primaryStage.setTitle("Student Information System");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
        StartController.getStage(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
