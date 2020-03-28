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
public class ChangeUsernameController implements Initializable {

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    @FXML
    private TextField CurrentUserN;

    @FXML
    private TextField NewUserN;

    @FXML
    private Label wrong;

    String conUrl = Database.conUrl;
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cancel.setOnAction(e -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
        ok.setOnAction(e -> {
            if (NewUserN.getText() != "") {

                try {
                    connection = DriverManager.getConnection(conUrl);
                } catch (SQLException ex) {
                    System.out.println("DriverManager error");
                    // error connection
                }
                try {
                    String update = "UPDATE ADMIN SET USERNAME=? WHERE USERNAME = ?";
                    stmt = connection.prepareStatement(update);
                    stmt.setString(1, NewUserN.getText());
                    stmt.setString(2, CurrentUserN.getText());
                    stmt.execute();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex);
                    System.out.println("failed to update");
                }
                ChangePasswordController.username = NewUserN.getText();
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            } else {
                wrong.setVisible(true);
                CurrentUserN.setText("");
                NewUserN.setText("");
            }
        });
    }

}
