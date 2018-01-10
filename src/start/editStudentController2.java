package start;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;

import java.io.File;
import java.sql.*;
import javafx.event.*;

public class editStudentController2 {
    private static Stage stage;
    private static Scene previousScene,currentScene;

    //Variable Declaration
    private static long rollNo=0,erpId=0,phoneNo;
    private String name,password,branch,course,emailId;
    private double dueFees=0.0,paidFees=0.0;
    private String imagePath=null;

    //Control Declaration
    @FXML
    private Label nameL,passL,courseL,branchL,rollNoL,emailL,phoneL,remFeesL,erpIdL;

    //Image Handling
    private static Image image;
    @FXML
    private ImageView profileImage;

    @FXML
    private Label response=new Label();
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private Button uploadProfilePic;

    @FXML
    public JFXTextField nameT;

    @FXML
    public JFXTextField branchT,rollT,courseT,emailT,phoneT,erpIdT,dueFeeT,paidFeeT;
    public JFXPasswordField passT;

    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    protected void getStage(Stage stage1, Scene scene, Scene s2,long r)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
        rollNo=r;
    }

    @FXML
    public void handleSave(ActionEvent ae)throws Exception
    {
        int i=-1;
        //Initializaing variables
        name=nameT.getText();
        password=passT.getText();
        branch=branchT.getText();
        long r=rollNo;
        rollNo=Long.parseLong(rollT.getText());
        course=courseT.getText();
        emailId=emailT.getText();
        phoneNo=Long.parseLong(phoneT.getText());
        erpId=Long.parseLong(erpIdT.getText());
        //dueFees=Double.parseDouble(dueFeeT.getText());
        //paidFees=Double.parseDouble(paidFeeT.getText());

        //response.setText("Name:"+name+" Id:"+rollNo+"  Password:"+password+" Course:"+course+" Branch:"+branch+"   RollNumber:"+rollNo+" Email:"+emailId+" Phone:"+phoneNo+" Erp:"+erpId);

        //Saving the progress in the database
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="update student set Id="+rollNo+",Name='"+name+"',Password='"+password+"',ERP="+erpId+",course='"+course+"',branch='"+branch+"',emailId='"+emailId+"',contactNo="+phoneNo+",profileAdd='"+imagePath+"' where Id='"+r+"'";
            //String query="update student set profileAdd='"+imagePath+"'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            //ResultSet rs=st.executeQuery(query);
            i=st.executeUpdate(query);
            if(i>0)
            {
                response.setText("Profile Saved");
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
    @FXML
    public void uploadProfile(ActionEvent ae)throws Exception
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file=chooser.showOpenDialog(new Stage());
        if(file!=null){
            //imagePath=file.getAbsolutePath();
            imagePath=file.toURI().toURL().toString();
            image=new Image(imagePath);
            profileImage.setImage(image);
            response.setText("Image Uploaded Successfully");
        }
        else
        {
            response.setText("Image unable to load");
        }
    }

    @FXML
    public void initialize()
    {

        //Fetching Student from database
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="select * from student where Id='"+rollNo+"'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            if(rs.next()) {
                name = rs.getString(2);
                password = rs.getString(3);
                erpId = Long.parseLong(rs.getString(4));
                paidFees = Double.parseDouble(rs.getString(5));
                dueFees = Double.parseDouble(rs.getString(6));
                branch = rs.getString(7);
                phoneNo = Long.parseLong(rs.getString(8));
                imagePath = rs.getString(9);
                course = rs.getString(10);
                emailId = rs.getString(11);

                //Initializing TextFields
                rollT.setText("" + rollNo);
                nameT.setText(name);
                passT.setText(password);
                erpIdT.setText(""+erpId);
                //paidFeeT.setText(""+paidFees);
                //dueFeeT.setText(""+dueFees);
                branchT.setText(branch);
                phoneT.setText(""+phoneNo);
                courseT.setText(course);
                emailT.setText(emailId);

                if(imagePath!=null)
                {
                    Image image=new Image(imagePath);
                    profileImage.setImage(image);
                }

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
