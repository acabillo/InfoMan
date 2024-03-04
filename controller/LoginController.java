package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;





    
    
public class LoginController {

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;

    @FXML
    TextField mytextfield;

    @FXML
    PasswordField mypasswordfield;

    @FXML
    Button mybutton;

    @FXML
    Label mywarninglabel;

    @FXML
    AppointmentListController dashboardController = null;

    @FXML
    FXMLLoader loader;
    

    public void login(ActionEvent event) throws IOException {
        String admin_username = mytextfield.getText();
        String admin_password = mypasswordfield.getText();

        if (DatabaseHandler.validateLogin(admin_username, admin_password)) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign In");
        
        alert.setHeaderText("Sign in Invalid");
        alert.setContentText("");
        alert.showAndWait();
        }
    }

}





