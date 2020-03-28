/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import static clinic.PatientViewDataController.ch_id;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import tablesClasses.Checkup;
import tablesClasses.CheckupPageTableRow;
import tablesClasses.Patient;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class WriteReportController implements Initializable {

    @FXML
    private Label Pname;
    @FXML
    private TextArea Report;
    @FXML
    private Button Psave;
    @FXML
    private Button cancel;

    String conUrl = "jdbc:sqlite:D:/database.db";
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    static int ch_id = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println(ch_id);
        Database db = new Database();
        Patient patient = db.getPatientformPcheck(ch_id);
        Pname.setText(patient.getP_name());

    }

    @FXML
    private void onPsave(ActionEvent event) {
        Database db = new Database();
        int result = db.setReportCheckup(ch_id, Report.getText());
        if (result == 0) {
            System.out.println("Done");
        }
    }
    
   

}
