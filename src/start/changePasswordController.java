package start;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import java.sql.*;
public class changePasswordController {
    static Stage stage;
    static Scene previousScene,currentScene;
    //Variable Declaration
    String id,changePassword,confirmPassword;
    //Control Declaration
    @FXML
    private JFXTextField userIdT;

    @FXML
    private JFXPasswordField changePasswordT,confirmPasswordT;
    @FXML
    private JFXButton save,back;
    @FXML
    private Label response;

    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    @FXML
    private void handleSaveButton(ActionEvent ae)throws Exception
    {
        id=userIdT.getText();
        changePassword=changePasswordT.getText();
        confirmPassword=confirmPasswordT.getText();

        if(!changePassword.equals(confirmPassword))
        {
            response.setText("New Password does not match");
            return;
        }
        else
        {
            int i=-1;
            //Changing Password
            try
            {
                String url="jdbc:mysql://localhost:3306/studentinfosystem";
                String uname="root";
                String pass="root123";
                String query="update student set Password='"+confirmPassword+"' where Id='"+id+"'";
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection(url,uname,pass);
                Statement st=con.createStatement();
                //ResultSet rs=st.executeQuery(query);
                i=st.executeUpdate(query);
                if(i>0)
                {
                    response.setText("Password Changed Successfully");
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
    protected static void getStage(Stage stage1,Scene scene,Scene s2)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
    }
}
