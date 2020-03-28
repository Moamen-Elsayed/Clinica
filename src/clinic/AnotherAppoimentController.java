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
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class AnotherAppoimentController implements Initializable {

    private Button GoToPatientInfo;
    private Button exit;
    @FXML
    private TableView<?> DrugTable;
    @FXML
    private Button AddDrug;
    @FXML
    private Button ChooseDrug;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //دا الاكشن بتاع زرار الغاء الامر 
        exit.setOnAction(e -> {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });

        // دا الاكشن بتاع زرار عرض بيانات المريض
        GoToPatientInfo.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PatientViewData.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(AnotherAppoimentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        });

    }

}
