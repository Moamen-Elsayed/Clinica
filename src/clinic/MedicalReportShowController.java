/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tablesClasses.*;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class MedicalReportShowController implements Initializable {

    @FXML
    private Label Pname;
    @FXML
    private TableView<Checkup> ReportsTable;
    @FXML
    private TableColumn<Checkup, String> ReportsCol;
    static int ch_id = 0;
    static String p_name = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Database db = new Database();
        ObservableList<Checkup> list = FXCollections.observableArrayList();
        ArrayList<P_check> p_checks;
        Patient patient;
        if (!p_name.equals("")) {
            patient = db.getPatient(p_name);
            p_checks = db.getAllPatientCheckups(patient.getP_id());
        } else {
            patient = db.getPatientformPcheck(ch_id);
            p_checks = db.getAllPatientCheckups(patient.getP_id());
        }
        for (int i = 0; i < p_checks.size(); i++) {
            list.add(db.getCheckup(p_checks.get(i).getFk_ch_id()));
        }
        ReportsCol.setCellValueFactory(new PropertyValueFactory("report"));
        ReportsTable.getItems().addAll(list);
        Pname.setText(patient.getP_name());
    }

}
