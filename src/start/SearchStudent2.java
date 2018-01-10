package start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import java.sql.*;
public class SearchStudent2 {
    private static Stage stage;
    private static Scene previousScene,searchStudentScene2;

    //Variable Declaration
    private static String imagePath=null;
    private static long id,erp;
    private static double attendance;
    private static String name,branch,course,email;

    //Controls Declaration
    @FXML
    private ImageView profileImage;
    @FXML
    private Button back;
    @FXML
    private Label response=new Label();
    @FXML
    private Label nameL,IDL,erpL,courseL,branchL,emailL;
    @FXML
    private Label attendanceL;
    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    protected static void getStage(Stage stage1, Scene scene,long ID)
    {
        stage=stage1;
        previousScene=scene;
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
            branch=rs.getString(7);
            imagePath=rs.getString(9);
            course=rs.getString(10);
            email=rs.getString(11);
            attendance=rs.getDouble(12);

            IDL.setText(""+id);
            nameL.setText(name);
            erpL.setText(""+erp);
            branchL.setText(branch);
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
