package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientListController implements Initializable {

    @FXML
    private TableView<Patient> patientList;
    @FXML
    private TableColumn<Patient, String> idclmn;
    @FXML
    private TableColumn<Patient, String> nameclmn;
    @FXML
    private TableColumn<Patient, String> birthdateclmn;
    @FXML
    private TableColumn<Patient, String> contactclmn;
    @FXML
    private TableColumn<Patient, String> recordclmn;
    @FXML
    private TableColumn<Patient, String> dentistclmn;

    int id;

    @FXML
    private TextField name, date, birthdate, contact, record, dentist;
    public void showPatient() {
        patientList();
    }

    public void patientList() {
        PreparedStatement pst;
        Connection con = getDBConnection();
        ObservableList<Patient> patient = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select patient_id, patient_name, patient_birthdate, patient_contact, patient_record, patient_dentist from tbl_patients");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {

            Patient app = new Patient(null, null, null, null, null, null);
    app.setId(rs.getString("patient_id"));
    app.setName(rs.getString("patient_name"));
    app.setBirthdate(rs.getString("patient_birthdate"));
    app.setContact(rs.getString("patient_contact"));
    app.setRecord(rs.getString("patient_record"));
    app.setDentist(rs.getString("patient_dentist"));

    patient.add(app);

       }
    } 
    patientList.setItems(patient);
               
       }
       
       catch (SQLException ex) 
       {
        java.util.logging.Logger.getLogger(PatientCreate.class.getName()).log(Level.SEVERE, null, ex);
    }

    idclmn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    nameclmn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    birthdateclmn.setCellValueFactory(cellData -> cellData.getValue().birthdateProperty());
    contactclmn.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
    recordclmn.setCellValueFactory(cellData -> cellData.getValue().recordProperty());
    dentistclmn.setCellValueFactory(cellData -> cellData.getValue().dentistProperty());

             patientList.setRowFactory( tv -> {
             TableRow<Patient> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                int myIndex;
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  patientList.getSelectionModel().getSelectedIndex();
         
                   id = Integer.parseInt(String.valueOf(patientList.getItems().get(myIndex).getId()));
                   name.setText(patientList.getItems().get(myIndex).getName());
                   birthdate.setText(patientList.getItems().get(myIndex).getBirthdate());
                   contact.setText(patientList.getItems().get(myIndex).getContact());
                   record.setText(patientList.getItems().get(myIndex).getRecord());
                   dentist.setText(patientList.getItems().get(myIndex).getDentist());

                           
                         
                           
                }
             });
                return myRow;
                   });
    
    
      }


public static Connection getDBConnection()
    {
        Connection connection = null;
        String dburl = "jdbc:mysql://localhost:3306/fencdb";
        String userName = "root";
        String password = "esfenada";

        try {
            connection = DriverManager.getConnection(dburl, userName, password);
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
        return connection;
    }

 @Override
public void initialize(URL location, ResourceBundle resources) {
    // Initialize table columns
    // idclmn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameclmn.setCellValueFactory(new PropertyValueFactory<>("name"));
    birthdateclmn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    contactclmn.setCellValueFactory(new PropertyValueFactory<>("contact"));
    recordclmn.setCellValueFactory(new PropertyValueFactory<>("record"));
    dentistclmn.setCellValueFactory(new PropertyValueFactory<>("dentist"));
    getDBConnection();
    patientList();

    
}


    @FXML
    public void dashboardpage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void adminpage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void appointmentpage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentList.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void book(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/PatientCreate.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void dentistpage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Dentists.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void patientpage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/PatientsList.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void servicepage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Services.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    
}
