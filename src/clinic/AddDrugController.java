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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tablesClasses.Drug;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class AddDrugController implements Initializable {

    @FXML
    private TextField M_name;
    @FXML
    private Button Add;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onAdd(ActionEvent event) {
        String name = M_name.getText();
        Database db = new Database();
        int result = db.insertInDrug(new Drug(name));
        if (result == 0) {
            System.out.println("done");
            onCancel(null);
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
