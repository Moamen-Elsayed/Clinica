/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tablesClasses.Check_drug;
import tablesClasses.CheckupPageTableRow;
import tablesClasses.Drug;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class DrugController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Drug> DrugTable;
    @FXML
    private Button AddDrug;
    @FXML
    private Button ChooseDrug;
    @FXML
    private TableColumn<Drug, String> DrugCol;
    URL url =null;
    ResourceBundle rb = null;
    static int ch_id =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
        this.rb = rb;
        DrugCol.setCellValueFactory(new PropertyValueFactory("drug_name"));
        Database db = new Database();
        DrugTable.getItems().clear();
        ObservableList<Drug> drugs = db.getDrug();
        DrugTable.getItems().addAll(drugs);

    }

    @FXML
    private void onAdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddDrug.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        initialize(url, rb);
    }

    @FXML
    private void onSelect(ActionEvent event) {
        Drug selectedRow = DrugTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("يجب اختيار عنصر من الجدول اولا");
            alert.show();
            return ;
        }
        int drug_id = selectedRow.getDrug_id();
        Database db = new Database();
        int result = db.insertInCheck_drug(new Check_drug(ch_id, drug_id));
        if (result == 0) {
            System.out.println("done");
            Stage stage = (Stage) ChooseDrug.getScene().getWindow();
            stage.close();
        }
    }

}
