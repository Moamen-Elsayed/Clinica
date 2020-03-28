/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tablesClasses.*;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class LatestReportController implements Initializable {

    @FXML
    private TextField patientNameField;
    @FXML
    private TextArea latestReport;
    
    static int ch_id = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Database db = new Database();
        String patientName = db.getPatientformPcheck(ch_id).getP_name();
        String report = db.getCheckup(ch_id).getReport();
        patientNameField.setText(patientName);
        latestReport.setText(report);
    }    
    
}
