package start;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.event.*;

import java.io.*;
import java.sql.*;

public class addStudentController {

    static Stage stage;
    static Scene previousScene,currentScene;

    //Variable Declaration
    private long rollNo=0,erpId=0,phoneNo;
    private String name,password,branch,course,emailId;
    private double dueFees=0.0,paidFees=0.0,attendance=0.0;

    //Control Declaration
    @FXML
    private Label nameL,passL,courseL,branchL,rollNoL,emailL,phoneL,remFeesL,erpIdL;

    //Image Handling
    private static Image image;
    @FXML
    private ImageView profileImage;
    String imagePath=null;

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
    private JFXTextField attendanceT;

    @FXML
    public JFXTextField branchT,rollT,courseT,emailT,phoneT,erpIdT,dueFeeT,paidFeeT;
    public JFXPasswordField passT;

    @FXML
    private void handleBackButton(ActionEvent ae)throws Exception
    {
        stage.setScene(previousScene);
        stage.show();
    }
    @FXML
    public void handleSave(ActionEvent ae)throws Exception
    {
        int i=-1;
        //Initializaing variables
        name=nameT.getText();
        password=passT.getText();
        branch=branchT.getText();
        rollNo=Long.parseLong(rollT.getText());
        course=courseT.getText();
        emailId=emailT.getText();
        phoneNo=Long.parseLong(phoneT.getText());
        erpId=Long.parseLong(erpIdT.getText());
        dueFees=Double.parseDouble(dueFeeT.getText());
        paidFees=Double.parseDouble(paidFeeT.getText());
        attendance=Double.parseDouble(attendanceT.getText());


        //Saving the progress in the database
        try
        {
            String url="jdbc:mysql://localhost:3306/studentinfosystem";
            String uname="root";
            String pass="root123";
            String query="insert into student "+"values("+rollNo+",'"+name+"','"+password+"',"+erpId+","+paidFees+","+dueFees+",'"+branch+"',"+phoneNo+",'"+imagePath+"','"+course+"','"+emailId+"',"+attendance+")";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,uname,pass);
            Statement st=con.createStatement();
            //ResultSet rs=st.executeQuery(query);
            i=st.executeUpdate(query);
            if(i>0)
            {
                response.setText("Student Added Successfully");
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
    protected static void getStage(Stage stage1,Scene scene,Scene s2)
    {
        stage=stage1;
        previousScene=scene;
        currentScene=s2;
    }
}