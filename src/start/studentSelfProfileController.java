package start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.event.*;
import java.io.*;
import java.sql.*;
public class studentSelfProfileController {
    //Variable Declaration
    private static String imagePath=null;
    private static long id,erp,phone;
    private static double paidF,dueF,attendance;
    private static String name,branch,course,email;

    //Control Declaration
    static Stage stage;
    static Scene previousScene,currentScene;
    @FXML
    private ImageView profileImage;
    @FXML
    private Button back;
    @FXML
    private Label response=new Label();
    @FXML
    private Label nameL,idL,erpL,courseL,branchL,phoneL,emailL,paidFeesL,dueFeesL;
    @FXML
    private Label attendanceL;

    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    protected  void getStage(Stage stage1,Scene scene,Scene s2,long ID)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
        id=ID;
    }
    @FXML
    public void initialize() {
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
            rs.next();

                name=rs.getString(2);
                erp=rs.getInt(4);
                paidF=rs.getDouble(5);
                dueF=rs.getDouble(6);
                branch=rs.getString(7);
                phone=rs.getLong(8);
                imagePath=rs.getString(9);
                course=rs.getString(10);
                email=rs.getString(11);
                attendance=rs.getDouble(12);

                idL.setText(""+id);
                nameL.setText(name);
                erpL.setText(""+erp);
                paidFeesL.setText(""+paidF);
                dueFeesL.setText(""+dueF);
                branchL.setText(branch);
                phoneL.setText(""+phone);
                courseL.setText(""+course);
                emailL.setText(email);
                attendanceL.setText(""+attendance+"%");

                if(imagePath!=null)
                {
                    Image image=new Image(imagePath);
                    profileImage.setImage(image);
                }
            st.close();
            con.close();
        }
        catch(Exception e)
        {
            //response.setText("Exception Occurred");
        }
    }
}
