package start;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.IOException;

public class StartController {
    static Stage stage;
    static Scene startScene;

    //Declaration of controls
    @FXML
    public Button adminLoginButton;
    public Button studentLoginButton;
    //Listeners
    @FXML
    public void handleAdminButton(ActionEvent ae) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/adminLoginLayout.fxml"));
        //adminLoginController adminLoginController=new adminLoginController(stage,startScene);
        stage.setScene(new Scene(root, 1920, 1080));
        stage.show();
        adminLoginController adminLoginController=new adminLoginController();
        adminLoginController.getStage(stage,startScene,stage.getScene());
    }
    @FXML
    public void handleStudentButton(ActionEvent ae)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/studentLoginLayout.fxml"));
        stage.setScene(new Scene(root, 1920, 1080));
        stage.show();
        studentLoginController.getStage(stage,startScene,stage.getScene());

    }
    //Method to get Stage from previous Scene
    protected static void getStage(Stage primaryStage)
    {
        stage=primaryStage;
        startScene=primaryStage.getScene();
    }
}
