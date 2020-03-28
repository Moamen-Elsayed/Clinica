/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import static clinic.Database.conUrl;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Owner
 */
public class ChangePasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button ok;

    @FXML
    private Button close;

    @FXML
    private PasswordField CurrentPass;

    @FXML
    private PasswordField NewPass;

    @FXML
    private PasswordField RenewPass;

    @FXML
    private Label wrong;

    String conUrl = Database.conUrl;
    Connection connection = null;

    ResultSet resultSet;
    static String username = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        close.setOnAction(e -> {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });

    }

    @FXML
    private void onOk(ActionEvent event) {
        if (NewPass.getText().equals(RenewPass.getText()) && !NewPass.getText().equals("") && !RenewPass.getText().equals("")) {
            System.out.println("con");
            try {
                connection = DriverManager.getConnection(conUrl);
            } catch (SQLException ex) {
                System.out.println("DriverManager error");
                // error connection
            }
            try {
                String update = "UPDATE ADMIN SET PASSWORD = ? WHERE USERNAME = ? ";
                PreparedStatement stmt = connection.prepareStatement(update);
                stmt.setString(1, NewPass.getText());
                stmt.setString(2, username);
                // stmt.setString(3, username);
                stmt.execute();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex);
                System.out.println("failed to update");
            }
            Stage stage = (Stage) ok.getScene().getWindow();
            stage.close();
        } else {
            wrong.setVisible(true);
            NewPass.setText("");
            RenewPass.setText("");
        }
    }

}
