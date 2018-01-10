package start;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;
import java.sql.*;

public class adminLoginController {
    private static Stage stage;
    private static Scene previousScene,adminLoginScene,adminTasksScene;

    @FXML
    private JFXTextField userT;
    @FXML
    private JFXPasswordField pswdT;
    @FXML
    private Label response;
    @FXML
    private Button signIn,back;
    @FXML
    private void handleLoginButton(ActionEvent actionEvent)throws Exception {
                //Initialization
                String userId=userT.getText();
                String password=pswdT.getText();

                //Connecting to database
                try
                {
                    String url="jdbc:mysql://localhost:3306/studentinfosystem";
                    String uname="root";
                    String pass="root123";
                    String query="select * from admin where Id='"+userId+"'";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(url,uname,pass);
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(query);

                    rs.next();
                    if(rs.getString(3).equals(password))
                    {

                        Parent root = FXMLLoader.load(getClass().getResource("Resources/adminTasksLayout.fxml"));
                        adminTasksScene=new Scene(root, 1920, 1080);
                        stage.setScene(adminTasksScene);
                        stage.show();
                        adminTasksController.getStage(stage,adminLoginScene,adminTasksScene);
                        //response.setText("Login Successful");
                    }
                    else
                    {
                        response.setText("Incorrect Username or Password");
                    }
                    st.close();
                    con.close();
                }

                catch(Exception e)
                {
                    response.setText("Student Not Found");
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
        adminLoginScene=s2;
    }

}
