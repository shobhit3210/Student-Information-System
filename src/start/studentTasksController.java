package start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class studentTasksController {
    private static Stage stage;
    private static Scene previousScene,currentScene,editStudentLayoutScene2,changePasswordScene,viewProfileScene,viewNoticeScene,searchStudentScene;
    //Variable Declaration
    private static long id;

    //Controls Declaration
    @FXML
    private Button Logout,back;
    @FXML
    private Button seeOtherStudentB,editProfileB,viewProfileB,changePasswordB,logout;
    @FXML
    private Button viewNotice;

    protected void getStage(Stage stage1,Scene scene,Scene s2,long ID)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
        id=ID;

    }
    @FXML
    private void backButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }

    @FXML
    private void handleEditButton(ActionEvent ae)throws Exception {

        //Searching Student
        try {
            String url = "jdbc:mysql://localhost:3306/studentinfosystem";
            String uname = "root";
            String pass = "root123";
            String query = "select * from student where Id='" + id + "'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {

                editStudentController2 editStudent = new editStudentController2();
                editStudent.getStage(stage, currentScene, editStudentLayoutScene2, id);
                editStudent.initialize();

                Parent root = FXMLLoader.load(getClass().getResource("Resources/editStudentLayout2.fxml"));
                editStudentLayoutScene2 = new Scene(root, 1920, 1080);

                stage.setScene(editStudentLayoutScene2);
                stage.show();
            }

                //else

                //{
                  //  response.setText("Student Not Found");
                //}
                st.close();
                con.close();
            }
            catch(Exception e)
            {
                //response.setText("Exception Occurred");
            }

    }
    @FXML
    private void handleChangePassword(ActionEvent ae)throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/changePasswordLayout.fxml"));
        changePasswordScene=new Scene(root, 1920, 1080);

        changePasswordController changePassword2=new changePasswordController();
        changePassword2.getStage(stage,currentScene,changePasswordScene);

        stage.setScene(changePasswordScene);
        stage.show();
    }
    @FXML
    private void viewProfile(ActionEvent ae)throws Exception
    {
        studentSelfProfileController viewProfile=new studentSelfProfileController();
        viewProfile.getStage(stage,currentScene,changePasswordScene,id);
        viewProfile.initialize();

        Parent root = FXMLLoader.load(getClass().getResource("Resources/studentSelfProfileLayout.fxml"));
        viewProfileScene=new Scene(root, 1920, 1080);

        stage.setScene(viewProfileScene);
        stage.show();
    }
    @FXML
    private void handleNotice(ActionEvent ae)throws Exception
    {
        viewNoticeController notice=new viewNoticeController();
        notice.getStage(stage,currentScene);
        notice.initialize();

        Parent root = FXMLLoader.load(getClass().getResource("Resources/viewNotice.fxml"));
        viewNoticeScene=new Scene(root, 1920, 1080);

        stage.setScene(viewNoticeScene);
        stage.show();
    }
    @FXML
    private void searchStudent(ActionEvent ae)throws Exception
    {
        SearchStudent student=new SearchStudent();
        student.getStage(stage,currentScene,currentScene);

        Parent root = FXMLLoader.load(getClass().getResource("Resources/searchStudent1.fxml"));
        searchStudentScene=new Scene(root, 1920, 1080);

        stage.setScene(searchStudentScene);
        stage.show();
    }
}
