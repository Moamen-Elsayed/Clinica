/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tablesClasses.CheckupPageTableRow;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class DailyReportController implements Initializable {

    @FXML
    private Label TodayDate;
    @FXML
    private TableView<CheckupPageTableRow> DailyReportTable;
    @FXML
    private Label DailyTotal;
    @FXML
    private TableColumn<CheckupPageTableRow, String> Pname;
    @FXML
    private TableColumn<CheckupPageTableRow, String> Vtype;
    @FXML
    private TableColumn<CheckupPageTableRow, String> Vcost;

    /**
     * Initializes the controller class.
     */
    static int ch_id = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);
        String conUrl = "jdbc:sqlite:D:/clinic Database/database.db";
        Database db = new Database();
        Pname.setCellValueFactory(new PropertyValueFactory("patientName"));
        Vtype.setCellValueFactory(new PropertyValueFactory("visitType"));
        Vcost.setCellValueFactory(new PropertyValueFactory("cost"));
        ObservableList<CheckupPageTableRow> checkupPageTableRows = db.getCheckupDataForTable(currentDate);
        DailyReportTable.getItems().addAll(checkupPageTableRows);
        int sum = 0;
        String sqlString1 = "SELECT * FROM  checkup";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        try {
            ResultSet resultSet = connection.prepareStatement(sqlString1).executeQuery();
            while (resultSet.next()) {
                String ch_date = resultSet.getString("ch_date");
                if (ch_date.contains(currentDate)) {
                    sum += resultSet.getInt("cost");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DailyReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DailyTotal.setText(String.valueOf(sum));
    }

}
