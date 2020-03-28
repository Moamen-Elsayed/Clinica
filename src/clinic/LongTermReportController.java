/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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
public class LongTermReportController implements Initializable {

    @FXML
    private DatePicker FromDP;
    @FXML
    private DatePicker ToDP;
    @FXML
    private Label LongTermTotal;
    @FXML
    private TableColumn<CheckupPageTableRow, String> PatientNameCol;
    @FXML
    private TableColumn<CheckupPageTableRow, String> VisitTypeCol;
    @FXML
    private TableColumn<CheckupPageTableRow, String> CostCol;
    @FXML
    private TableView<CheckupPageTableRow> ReportTable;
    LocalDate from, to;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        PatientNameCol.setCellValueFactory(new PropertyValueFactory("patientName"));
        VisitTypeCol.setCellValueFactory(new PropertyValueFactory("visitType"));
        CostCol.setCellValueFactory(new PropertyValueFactory("cost"));
        FromDP.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldDate, LocalDate newDate) {
                from = newDate;
                if (to != null && from.isBefore(to)) {
                    int sum = 0;
                    LocalDate dateCounter = from;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    Database db = new Database();
                    ArrayList<LocalDate> totalDates = new ArrayList<>();
                    ObservableList<CheckupPageTableRow> checkupPageTableRows = FXCollections.observableArrayList();
                    while (!dateCounter.isAfter(to)) {
                        totalDates.add(dateCounter);
                        checkupPageTableRows.addAll(db.getCheckupDataForTable(dateCounter.format(dtf).toString()));
                        dateCounter = dateCounter.plusDays(1);
                    }
                    ReportTable.getItems().clear();
                    ReportTable.getItems().addAll(checkupPageTableRows);
                    for (int i = 0; i < checkupPageTableRows.size(); i++) {
                        sum += checkupPageTableRows.get(i).getCost();
                    }
                    LongTermTotal.setText(sum + "");

                }
            }
        });
        ToDP.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldDate, LocalDate newDate) {
                to  = newDate;
                if (from != null && from.isBefore(to)) {
                    int sum = 0;
                    LocalDate dateCounter = from;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    Database db = new Database();
                    ArrayList<LocalDate> totalDates = new ArrayList<>();
                    ObservableList<CheckupPageTableRow> checkupPageTableRows = FXCollections.observableArrayList();
                    while (!dateCounter.isAfter(to)) {
                        totalDates.add(dateCounter);
                        checkupPageTableRows.addAll(db.getCheckupDataForTable(dateCounter.format(dtf).toString()));
                        dateCounter = dateCounter.plusDays(1);
                    }
                    ReportTable.getItems().clear();
                    ReportTable.getItems().addAll(checkupPageTableRows);
                    for (int i = 0; i < checkupPageTableRows.size(); i++) {
                        sum += checkupPageTableRows.get(i).getCost();
                    }
                    LongTermTotal.setText(sum + "");

                }
            }
        });
    }

}
