package start;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.*;
import java.sql.*;

public class studentLoginController {
    private static Stage stage;
    private static Scene previousScene,studentLoginScene,studentTasksScene;
    private long userId;
    //Controls Declaration
    @FXML
    private JFXTextField userT;
    @FXML
    private JFXPasswordField pswdT;
    @FXML
    private Label response;
    @FXML
    private Button signIn,back;

    public static void getStage(Stage stage1,Scene scene,Scene s2)
    {
        stage=stage1;
        previousScene=scene;
        studentLoginScene=s2;
    }
    @FXML
    public void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    @FXML
    public void handleSignInButton(ActionEvent ae)throws Exception
    {
                //Initialization
                userId=Long.parseLong(userT.getText());
                String password=pswdT.getText();

                try
                {
                    String url="jdbc:mysql://localhost:3306/studentinfosystem";
                    String uname="root";
                    String pass="root123";
                    String query="select * from student where Id='"+userId+"'";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(url,uname,pass);
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(query);

                    rs.next();

                    if(rs.getString(3).equals(password))
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("Resources/studentTasksLayout.fxml"));
                        studentTasksScene=new Scene(root, 1920, 1080);
                        stage.setScene(studentTasksScene);
                        stage.show();
                        studentTasksController studentTasks=new studentTasksController();
                        studentTasks.getStage(stage,studentLoginScene,studentTasksScene,userId);
                        //response.setText("Login Successful");
                    }
                    else
                    {
                        response.setText("Login Unsuccessful");
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
