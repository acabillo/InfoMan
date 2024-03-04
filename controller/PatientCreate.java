package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

    
public class PatientCreate implements Initializable{


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
    @FXML
    private ChoiceBox<String> choicebox2;
    int id;

    @FXML
    private TextField name, birthdate, contact, record, dentist;


    @FXML

    public void create(ActionEvent event) throws IOException  {
        PreparedStatement pst;
        Connection connection = null;

        String dburl = "jdbc:mysql://localhost:3306/fencdb";
        String userName = "root";
        String password = "esfenada";

        try
        {
            connection = DriverManager.getConnection(dburl, userName, password);
            connection.setAutoCommit(true);

        } catch (Exception e){
            e.printStackTrace();
        }
        
        
        String patient_name, patient_birthdate, patient_contact, patient_record, patient_dentist;

            patient_name = name.getText();
            patient_birthdate = birthdate.getText();
            patient_contact = contact.getText();
            patient_record = record.getText();
            patient_dentist = choicebox2.getValue();
            
        try 
        {
            pst = connection.prepareStatement("insert into tbl_patients(patient_name, patient_birthdate, patient_contact, patient_record, patient_dentist) values (?,?,?,?,?)");
            pst.setString(1, patient_name);
            pst.setString(2, patient_birthdate);
            pst.setString(3, patient_contact);
            pst.setString(4, patient_record);
            pst.setString(5, patient_dentist);
            System.out.println("SQL Query: " + pst.toString()); // Print the SQL query for debugging

            pst.executeUpdate();
          
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patients");
        
        alert.setHeaderText("Patients");
        alert.setContentText("Patient has been successfully added!!");
        alert.showAndWait();
        patientList();

            
            name.setText("");
            birthdate.setText("");
            contact.setText("");
            record.setText("");
            name.requestFocus();
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    public void delete(ActionEvent event) {
        PreparedStatement pst;
        int myIndex;
        Connection con = getDBConnection();

        myIndex = patientList.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(patientList.getItems().get(myIndex).getId()));
                     
        try 
        {
            pst = con.prepareStatement("delete from tbl_patients where patient_id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patients");
        
            alert.setHeaderText("Patients");
            alert.setContentText("Patient Removed!");
            alert.showAndWait();

        patientList();
        name.setText("");
        birthdate.setText("");
        contact.setText("");
        record.setText("");
        } 
        catch (SQLException ex)
        {
            java.util.logging.Logger.getLogger(PatientCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void update(ActionEvent event) {

        PreparedStatement pst;
        Connection con = getDBConnection();
        int myIndex;
        myIndex = patientList.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(patientList.getItems().get(myIndex).getId()));

        String patient_name, patient_birthdate, patient_contact, patient_record, patient_dentist;
        int patient_id;

        patient_name = name.getText();
        patient_birthdate = birthdate.getText();
        patient_contact = contact.getText();
        patient_record = record.getText();
        patient_dentist = choicebox2.getValue();
       try 
       {
           pst = con.prepareStatement("update tbl_patients set patient_name = ?, patient_birthdate = ?, patient_contact = ?, patient_record = ?, patient_dentist = ? where patient_id = ?"); 
    
           pst.setString(1, patient_name);
           pst.setString(2, patient_birthdate);
           pst.setString(3, patient_contact);
           pst.setString(4, patient_record);
           pst.setString(5, patient_dentist);
           pst.setInt(6, id);
           System.out.println("SQL Query: " + pst.toString()); // Print the SQL query for debugging

           pst.executeUpdate();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Patients");
           alert.setHeaderText("Patients");
           alert.setContentText("Patient updated successfully!!");
           alert.showAndWait();

           patientList();
           name.setText("");
           birthdate.setText("");
           contact.setText("");
           record.setText("");
        
        }
       
       catch (SQLException ex) 
       {
        java.util.logging.Logger.getLogger(PatientCreate.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    String[] dentist = {"Dr. Dhan Lloyd Ferrer", "Dr. Francis Earl Celada", "Dra. Anthonette Mae Navarro", "Dra. Katherine Espinosa"};
    choicebox2.getItems().addAll(dentist);
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









