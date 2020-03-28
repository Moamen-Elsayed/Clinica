/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tablesClasses.Admin;
import tablesClasses.Doctor;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class DocInfoController implements Initializable {

    @FXML
    private TextField DPhone;
    @FXML
    private TextField DEmail;
    @FXML
    private TextField DSpec;
    @FXML
    private Button Create;
    @FXML
    private Button Cancel;
    @FXML
    private TextField DUName;
    @FXML
    private PasswordField DPass;
    @FXML
    private TextField DName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

    @FXML
    private void onCancel(ActionEvent event) {
        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DocInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage = (Stage) Cancel.getScene().getWindow();
            stage.close();
    }

    @FXML
    private void onCreate(ActionEvent event) {
        String username = DUName.getText();
        String password = DPass.getText();
        String doctorName = DName.getText();
        String phone = DPhone.getText();
        String email = DEmail.getText();
        String spec = DSpec.getText();
        Database db = new Database();
        int result = db.insertInDoctor(new Doctor(doctorName, phone, email, spec));
        if ( result == 0){
            int id = db.getSqliteSeq("doctor").getSeq();
            int result2 = db.insertInAdmin(new Admin(username, password, id));
            if(result2 == 0){ 
                System.out.println("done");
                onCancel(null);
            }
        }
    }
    
    
    
}
