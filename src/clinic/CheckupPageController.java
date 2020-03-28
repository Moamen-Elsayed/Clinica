/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tablesClasses.CheckupPageTableRow;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class CheckupPageController implements Initializable {

    @FXML
    private TextField Patient_Name;
    @FXML
    private Button bookanotherday;
    @FXML
    private Button cancellation;
    @FXML
    private Button latestreport;
    @FXML
    private Button refund;
    @FXML
    private Button pay;
    @FXML
    private Button arrival;
    @FXML
    private Button finish;
    @FXML
    private Button search;
    @FXML
    private Button anotherdate;
    @FXML
    private Button exit;
    @FXML
    private MenuItem Info;
    @FXML
    private MenuItem logout;
    @FXML
    private MenuItem sdad;
    @FXML
    private MenuItem Wsol;
    @FXML
    private MenuItem Enha2;
    @FXML
    private MenuItem dailyrevenue;
    @FXML
    private MenuItem longtermrevenue;
    @FXML
    private MenuItem medicine;
    @FXML
    private MenuItem test;
    @FXML
    private MenuItem report;
    @FXML
    private MenuItem passwordchande;
    @FXML
    private MenuItem changeusername;
    @FXML
    private MenuItem changereveal;
    @FXML
    private MenuItem changecon;
    @FXML
    private TableColumn<CheckupPageTableRow, String> nameCol;
    @FXML
    private TableColumn<CheckupPageTableRow, String> typeCol;
    @FXML
    private TableColumn<CheckupPageTableRow, String> checkupDateCol;
    @FXML
    private TableColumn<CheckupPageTableRow, String> endDateCol;
    @FXML
    private TableView<CheckupPageTableRow> checkupTable;
   
    URL url = null;
    ResourceBundle rb  =null;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button WReport;
    @FXML
    private Button refersh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
        this.rb = rb;
        nameCol.setCellValueFactory(new PropertyValueFactory("patientName"));
        typeCol.setCellValueFactory(new PropertyValueFactory("visitType"));
        checkupDateCol.setCellValueFactory(new PropertyValueFactory("checkupDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory("endDate"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        Database db = new Database();
        checkupTable.getItems().clear();
        ObservableList<CheckupPageTableRow> checkupPageTableRows = db.getCheckupDataForTable(currentDate);
        checkupTable.getItems().addAll(checkupPageTableRows);
        datePicker.setValue(now.toLocalDate());
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldDate, LocalDate newDate) {
                checkupTable.getItems().clear();
                ObservableList<CheckupPageTableRow> checkupPageTableRows = db.getCheckupDataForTable(newDate.format(dtf).toString());
                checkupTable.getItems().addAll(checkupPageTableRows);
            }
        });
    }


    @FXML
    private void BookAnotherDay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BookAnotherDay.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void LatestReport(ActionEvent event) throws IOException {
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        LatestReportController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LatestReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Refund(ActionEvent event) {
        Database db = new Database();
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        int ch_id = selectedRow.getCh_id();
        int cost = db.getCheckup(ch_id).getCost();
        ButtonType conf = new ButtonType("موافق");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "سيتم استرجاع مبلغ " + cost,new ButtonType("الغاء"));
        alert.getButtonTypes().add(conf);
        alert.setTitle("تاكيد");
        alert.setHeaderText("تاكيد");
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==conf){
            db.setCostCheckup(ch_id, 0);
        }
            
        
    }

    @FXML
    private void Pay(ActionEvent event) throws IOException {
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        PaymentController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Arrival(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Patient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Finish(ActionEvent event) {
        Database db = new Database();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        System.out.println(dtf.format(now));  
        int result = db.setFinishCheckup(selectedRow.getCh_id(), dtf.format(now));
        if(result == 0){ 
            System.out.println("Done");
            checkupTable.getItems().clear();
            initialize(url, rb);
        }
    }

    @FXML
    private void Search(ActionEvent event) throws IOException {
        PatientViewDataController_1.name = Patient_Name.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PatientViewData_1.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void AnotherDate(ActionEvent event) throws IOException {
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        AnotherDateController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnotherDate.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage = (Stage) exit.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void Cancellation(ActionEvent event) {
        Database db = new Database();  
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();  
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        int result = db.deleteFromCheckup(selectedRow.getCh_id());
        if(result == 0){ 
            System.out.println("Done");
            checkupTable.getItems().clear();
            initialize(url, rb);
        }
    }

    @FXML
    private void info(ActionEvent event) throws IOException {
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        PatientViewDataController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PatientViewData.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb); 
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        Exit(event);
    }

    @FXML
    private void DailyRevenue(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DailyReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void LongTermRevenue(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LongTermReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Medicine(ActionEvent event) throws IOException {
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        DrugController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Drug.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Test(ActionEvent event) throws IOException {
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        MedicalTestController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MedicalTest.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void Report(ActionEvent event) throws IOException {
        MedicalReportShowController.p_name = Patient_Name.getText();
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        MedicalReportShowController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MedicalReportShow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void PasswordChange(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void ChangeUserName(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeUsername.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void ChangeReveal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Rev.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void ChangeCon(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cons.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void WriteReport(ActionEvent event) throws IOException {
        Database db = new Database();
        CheckupPageTableRow selectedRow = checkupTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        WriteReportController.ch_id = selectedRow.getCh_id();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WriteReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void onRefresh(ActionEvent event) {
        initialize(url, rb);
    }
    
 

}
