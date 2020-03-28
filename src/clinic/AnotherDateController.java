/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tablesClasses.Patient;
import tablesClasses.PaymentPageTableRow;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */

public class AnotherDateController implements Initializable {

   @FXML
    private TableView PayTable;
    @FXML
    private TableColumn<PaymentPageTableRow, String> pNameCol;
    @FXML
    private TableColumn<PaymentPageTableRow, String> visitTypeCol;
    @FXML
    private TableColumn<PaymentPageTableRow, String> dateCol;
    @FXML
    private Button Cancel;
    
    static int ch_id = 0;
    
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
    }    

    @FXML
    private void onCancel(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }
    
}
