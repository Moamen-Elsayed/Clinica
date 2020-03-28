/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class LogInController implements Initializable {

    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private Button ok;
    @FXML
    private Button close;
    @FXML
    private Button CreateNewAcc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ok(ActionEvent event) throws IOException {
        Database db = new Database();
        String username = UserName.getText();
        String password = Password.getText();
        int checkResult = db.checkIfAdmin(username, password);
        switch(checkResult){
            case 0:
                System.out.println("wrong username or password");
                break;
            case 1:
                System.out.println("success");
                ChangePasswordController.username = username;
                Parent root = FXMLLoader.load(getClass().getResource("CheckupPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage = (Stage) CreateNewAcc.getScene().getWindow();
                stage.close();
                break;
            case 2:
                System.out.println("error");
                break;
        }
    }

    @FXML
    private void Close(ActionEvent event) throws IOException {
        Stage stage = (Stage) CreateNewAcc.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Create(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DocInfo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage = (Stage) CreateNewAcc.getScene().getWindow();
            stage.close();
    }

    
}
