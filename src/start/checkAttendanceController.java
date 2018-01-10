package start;

import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.stage.*;
import java.sql.*;

public class checkAttendanceController {
    static Stage stage;
    static Scene previousScene,currentScene;

    //Variable Declaration
    String id;

    //Control Declaration
    @FXML
    private Button back;
    @FXML
    private Button checkAttendance;

    @FXML
    private JFXTextField userIdT;
    @FXML
    private Label response;

    protected static void getStage(Stage stage1, Scene scene, Scene s2)
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
    private void attendance1(ActionEvent ae)throws Exception
    {
        id=userIdT.getText();
        double attendance;

        //Retrieving Attendance
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="select * from student where Id='"+id+"'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            if(rs.next())
            {
                attendance=rs.getDouble(12);
                response.setText("Total Attendance:"+attendance);
            }
            else
            {
                response.setText("Student does not exist");
            }

            st.close();
            con.close();
        }
        catch(Exception e)
        {
            response.setText("Exception Occurred");
        }
    }
}
