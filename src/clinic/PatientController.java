/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tablesClasses.*;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class PatientController implements Initializable {
    @FXML
    private Button Cancel;
    @FXML
    private ChoiceBox VType;
    @FXML
    private TextField Patient_Name;
    @FXML
    private TextField Patient_Age;
    @FXML
    private RadioButton Male;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton Female;
    @FXML
    private TextField Patient_District;
    @FXML
    private TextField Phone1;
    @FXML
    private TextField Phone2;
    @FXML
    private TextArea Notes;
    @FXML
    private Button Done;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VType.setItems(FXCollections.observableArrayList("كشف" , "استشارة"));

    }    

    @FXML
    private void onDone(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        int visit_id=0;
        String visit_type = VType.getValue().toString();
        if(visit_type.equals("كشف" )) visit_id =1;
        else if(visit_type.equals("استشارة")) visit_id =2;
        String name = Patient_Name.getText();
        int age = Integer.parseInt(Patient_Age.getText());
        String gender = ((RadioButton) Gender.getSelectedToggle()).getText();
        String district = Patient_District.getText();
        String phone1 = Phone1.getText();
        String phone2 = Phone2.getText();
        String med_history = Notes.getText();
        Database db = new Database();
        int resultPatient = db.insertInPatient(new Patient(name, age, med_history, gender, district));
        if ( resultPatient == 0){
            int p_id = db.getSqliteSeq("patient").getSeq();
            int resultP_phones1 = db.insertInP_phones(new P_phones(p_id,phone1));
            int resultP_phones2 = db.insertInP_phones(new P_phones(p_id,phone2));
            if(resultP_phones1 == 0 && resultP_phones2 == 0){ 
                int resultCheckup = db.insertInCheckup(new Checkup(date, "",0, med_history, visit_id));
                if ( resultPatient == 0 && resultCheckup == 0){
                    int ch_id = db.getSqliteSeq("checkup").getSeq();
                    int resultP_check = db.insertInP_check(new P_check(p_id, ch_id));
                    if(resultP_check == 0){
                        System.out.println("done");
                        onCancel(null);
                    }
                }
            }
        }
        
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    
}
