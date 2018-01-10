package start;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;

import java.sql.*;

public class SearchStudent {
    private static Stage stage;
    private static Scene previousScene,currentScene,searchStudentScene2;

    //Variable Declaration
    String id;
    //Control Declaration
    @FXML
    private Label response;
    @FXML
    private JFXTextField userIdT;
    @FXML
    private Button edit,back;

    @FXML
    private void handleSearchButton(ActionEvent ae)throws Exception
    {
        id=userIdT.getText();
        //Searching Student
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="select * from student where Id='"+id+"'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            if(rs.next())
            {
                long rId=rs.getLong(1);
                //response.setText("Student Found");

                SearchStudent2 student=new SearchStudent2();
                student.getStage(stage,currentScene,rId);
                student.initialize();

                Parent root = FXMLLoader.load(getClass().getResource("Resources/searchStudent2.fxml"));
                searchStudentScene2=new Scene(root, 1920, 1080);

                stage.setScene(searchStudentScene2);
                stage.show();
            }
            else
            {
                response.setText("Student Not Found");
            }
            st.close();
            con.close();
        }
        catch(Exception e)
        {
            response.setText("Exception Occurred");
        }
    }
    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    protected static void getStage(Stage stage1,Scene scene,Scene s2)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
    }
}
