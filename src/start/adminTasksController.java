package start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.*;


public class adminTasksController {
    static Stage stage;
    static Scene previousScene,currentScene,addStudentScene,deleteStudentScene,changePasswordScene,checkFeesScene,addNoticeScene,checkAttendanceScene;
    //Controls Initialization
    @FXML
    private Button addStd,checkFees,editStd,deleteStd,chngPswd,logout,back;

    protected static void getStage(Stage stage1,Scene scene,Scene s2)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
    }

    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    @FXML
    private void handleAddButton(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/addStudentLayout.fxml"));
        addStudentScene=new Scene(root, 1920, 1080);

        addStudentController addStudent=new addStudentController();
        addStudent.getStage(stage,currentScene,addStudentScene);

        stage.setScene(addStudentScene);
        stage.show();

        //addStudentController.initializeTextField();
    }
    @FXML
    private void handleCheckFeesButton(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/checkFeesLayout.fxml"));
        checkFeesScene=new Scene(root, 1920, 1080);

        checkFeesController checkFees=new checkFeesController();
        checkFees.getStage(stage,currentScene,checkFeesScene);

        stage.setScene(checkFeesScene);
        stage.show();
        //addStudentController.initializeTextField();
    }
    @FXML
    private void handleDeleteButton(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/deleteStudentLayout.fxml"));
        deleteStudentScene=new Scene(root, 1920, 1080);

        deleteStudentController deleteStudent=new deleteStudentController();
        deleteStudent.getStage(stage,currentScene,deleteStudentScene);

        stage.setScene(deleteStudentScene);
        stage.show();
        //addStudentController.initializeTextField();
    }
    @FXML
    private void handleLogout(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    @FXML
    private void handleChangePassword(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/changePasswordLayout.fxml"));
        changePasswordScene=new Scene(root, 1920, 1080);

        changePasswordController changePassword=new changePasswordController();
        changePassword.getStage(stage,currentScene,changePasswordScene);

        stage.setScene(changePasswordScene);
        stage.show();
    }
    @FXML
    private void addNotice(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/addNoticeLayout.fxml"));
        addNoticeScene=new Scene(root, 1920, 1080);

        addNoticeController notice=new addNoticeController();
        notice.getStage(stage,currentScene,addNoticeScene);

        stage.setScene(addNoticeScene);
        stage.show();
    }
    @FXML
    private void checkAttendance(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/checkAttendance.fxml"));
        checkAttendanceScene=new Scene(root, 1920, 1080);

        checkAttendanceController attendance=new checkAttendanceController();
        attendance.getStage(stage,currentScene,checkAttendanceScene);

        stage.setScene(checkAttendanceScene);
        stage.show();
    }

}
