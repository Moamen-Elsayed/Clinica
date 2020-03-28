/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tablesClasses.*;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class PatientViewDataController_1 implements Initializable {

    @FXML
    private Label PName;

    @FXML
    private Label PAge;

    @FXML
    private Label PGender;

    @FXML
    private Label PPhone1;

    @FXML
    private Label PPhone2;

    @FXML
    private Label Note;

    @FXML
    private Button close;
    @FXML
    private Label PDistrict;

    static String name = null;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(name);
        ArrayList<P_phones> list = new ArrayList<>();
        Database db = new Database();
        Patient patient = db.getPatient(name);
        if (patient != null) {
            list = db.getP_phonesList(patient.getP_id());
            PName.setText(patient.getP_name());
            PAge.setText(patient.getAge() + "");
            PGender.setText(patient.getGender());
            PDistrict.setText(patient.getDistrict());
            Note.setText(patient.getMed_history());
            PPhone1.setText(list.get(0).getPhone_no());
            PPhone2.setText(list.get(1).getPhone_no());
        }

    }

    @FXML
    private void onClose(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
