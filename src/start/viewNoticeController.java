package start;

import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class viewNoticeController {
    //Control Declaration
    protected static Stage stage;
    protected static Scene previousScene,currentScene;
    @FXML
    private Button back;
    @FXML
    private Label noticeL;
    @FXML
    private Label response=new Label();

    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    protected static void getStage(Stage stage1,Scene scene)
    {
        stage=stage1;
        previousScene=scene;
    }
    @FXML
    public void initialize()
    {
        String data;
        //Fetching Student from database
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="select * from notice where type='general'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            if(rs.next()) {
                data = rs.getString(2);

                //Initializing TextFields
                noticeL.setText(data);

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
