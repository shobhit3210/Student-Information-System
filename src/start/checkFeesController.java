package start;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import java.sql.*;
public class checkFeesController {
    String id;
    //Control Declaration
    protected static Stage stage;
    protected static Scene previousScene,currentScene;
    @FXML
    private Button back;
    @FXML
    private Button checkFees;
    @FXML
    private JFXTextField usedIdT;
    @FXML
    private Label response;

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
    @FXML
    private void handleCheckFee(ActionEvent ae)throws Exception
    {
        id=usedIdT.getText();
        double paid,due;
        //Retrieving Fees
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
                paid=rs.getDouble(5);
                due=rs.getDouble(6);
                response.setText("Paid Fees:"+paid+"\nDue Fees:"+due);
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
