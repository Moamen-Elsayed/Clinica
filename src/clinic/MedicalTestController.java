/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import static clinic.DrugController.ch_id;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tablesClasses.Check_drug;
import tablesClasses.Check_test;
import tablesClasses.Test;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class MedicalTestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Test> TestTable;

    @FXML
    private TableColumn<Test, String> TestCol;

    @FXML
    private Button AddTest;

    @FXML
    private Button ChooseTest;
    URL url = null;
    ResourceBundle rb = null;
    static int ch_id =0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
        this.rb = rb;
        TestCol.setCellValueFactory(new PropertyValueFactory("t_name"));
        Database db = new Database();
        TestTable.getItems().clear();
        ObservableList<Test> tests = db.getTest();
        TestTable.getItems().addAll(tests);
    }

    @FXML
    private void onAdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddTest.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void onSelect(ActionEvent event) {
        Test selectedRow = TestTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        int t_id = selectedRow.getT_id();
        Database db = new Database();
        int result = db.insertInCheck_test(new Check_test(ch_id, t_id));
        if (result == 0) {
            System.out.println("done");
            Stage stage = (Stage) ChooseTest.getScene().getWindow();
            stage.close();
        }
    }

}
