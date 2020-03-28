/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class RevController implements Initializable {

    @FXML
    private Label CurrentVal;
    @FXML
    private TextField NewVal;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;

    /**
     * Initializes the controller class.
     */
    String conUrl = Database.conUrl;
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultSet;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String sqlString = "SELECT cost FROM visit WHERE v_id = 1";

        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection Error");
        }
        try {
            resultSet = connection.prepareStatement(sqlString).executeQuery();

            while (resultSet.next()) {
                CurrentVal.setText(resultSet.getString("cost"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
        }

        Ok.setOnAction(e -> {
            try {
                String update = "UPDATE VISIT SET COST=? WHERE V_ID=1";
                stmt = connection.prepareStatement(update);
                stmt.setString(1, NewVal.getText());
                stmt.execute();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex);
                System.out.println("failed to select table");
            }
            Stage stage = (Stage) Ok.getScene().getWindow();
            stage.close();
        });

        Cancel.setOnAction(e -> {
            Stage stage = (Stage) Cancel.getScene().getWindow();
            stage.close();
        });

    }

}
