package start;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import java.sql.*;

public class addNoticeController {
    static Stage stage;
    static Scene previousScene,currentScene;

    //Variable Declaration
    String notice;

    //Control Declaration
    @FXML
    private Button addNotice;
    @FXML
    private JFXTextArea noticeT;
    @FXML
    private Label response;
    @FXML
    private Button back,addNoticeB;

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
    public void addNotice1(ActionEvent ae)throws Exception
    {
        int i=-1;
        //Initializing variables
        notice=noticeT.getText();

        //Saving the progress in the database
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="update notice set data='"+notice+"' where type='"+"general"+"'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            //ResultSet rs=st.executeQuery(query);
            i=st.executeUpdate(query);
            if(i>0)
            {
                response.setText("Notice Added Successfully");
            }
            else
            {
                response.setText("Unsuccessful");
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
