/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import static clinic.PatientViewDataController.ch_id;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tablesClasses.*;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField VCost;
    @FXML
    private TextField VDis;
    @FXML
    private TextField VPay;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    @FXML
    private Label VType;

    static int ch_id = 0;
    @FXML
    private TableView PayTable;
    @FXML
    private TableColumn<PaymentPageTableRow, String> pNameCol;
    @FXML
    private TableColumn<PaymentPageTableRow, String> visitTypeCol;
    @FXML
    private TableColumn<PaymentPageTableRow, String> dateCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Database db = new Database();
        Patient patient = db.getPatientformPcheck(ch_id);
        pNameCol.setCellValueFactory(new PropertyValueFactory("p_name"));
        visitTypeCol.setCellValueFactory(new PropertyValueFactory("visitType"));
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        ObservableList<PaymentPageTableRow> paymentPageTableRows = db.getPaymentDataForTable(patient.getP_id());
        PayTable.getItems().addAll(paymentPageTableRows);
        VType.setText(db.getVisit(db.getCheckup(ch_id).getFk_v_id()).getType());
        VCost.setText(db.getVisit(db.getCheckup(ch_id).getFk_v_id()).getCost()+"");
        VDis.setText(0+"");
        VPay.setText(db.getVisit(db.getCheckup(ch_id).getFk_v_id()).getCost()+"");
    }    

    @FXML
    private void onOk(ActionEvent event) {
        Database db = new Database();
        int dis;
        if(VDis.getText().equals("")){
            dis = 0;
        }else{
            dis = Integer.parseInt(VDis.getText());
        }
        int cost = Integer.parseInt(VCost.getText()) - dis;
        int result = db.setCostCheckup(ch_id, cost);
        if(result == 0){ 
            System.out.println("Done");
            onCancel(null);
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onVDis(KeyEvent event) {
        int dis;
        if(VDis.getText().equals("")){
            dis = 0;
        }else{
            dis = Integer.parseInt(VDis.getText());
        }
        int result = Integer.parseInt(VCost.getText()) - dis;
        VPay.setText(result+"");
    }
    
}
