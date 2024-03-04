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
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;




    
    
public class AppointmentListController implements Initializable{


    // @FXML
    // private Button add, edit, del;

    @FXML
    private TableView<Appointment> appointmentList;
    @FXML
    private TableColumn<Appointment, String> idclmn;
    @FXML
    private TableColumn<Appointment, String> nameclmn;
    @FXML
    private TableColumn<Appointment, String> dateclmn;
    @FXML
    private TableColumn<Appointment, String> timeclmn;
    @FXML
    private TableColumn<Appointment, String> serviceclmn;
    @FXML
    private TableColumn<Appointment, String> dentistclmn;

    int id;

    @FXML
    private TextField name, date, time, service, dentist;

    public void read() {
        appointmentList();
    }

    public void appointmentList() {
        
        PreparedStatement pst;
        Connection con = getDBConnection();
        ObservableList<Appointment> appointment = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select appointment_id, patient_name, appointment_date, appointment_time, service_name, dentist_name from tbl_appointments");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {

            Appointment app = new Appointment(null, null, null, null, null, null);
    app.setId(rs.getString("appointment_id"));
    app.setName(rs.getString("patient_name"));
    app.setDate(rs.getString("appointment_date"));
    app.setTime(rs.getString("appointment_time"));
    app.setService(rs.getString("service_name"));
    app.setDentist(rs.getString("dentist_name"));

    appointment.add(app);

       }
    } 
    appointmentList.setItems(appointment);
               
       }
       
       catch (SQLException ex) 
       {
        java.util.logging.Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, ex);
    }

    idclmn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    nameclmn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    dateclmn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    timeclmn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
    serviceclmn.setCellValueFactory(cellData -> cellData.getValue().serviceProperty());
    dentistclmn.setCellValueFactory(cellData -> cellData.getValue().dentistProperty());

             appointmentList.setRowFactory( tv -> {
             TableRow<Appointment> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                int myIndex;
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  appointmentList.getSelectionModel().getSelectedIndex();
         
                   id = Integer.parseInt(String.valueOf(appointmentList.getItems().get(myIndex).getId()));
                   name.setText(appointmentList.getItems().get(myIndex).getName());
                   date.setText(appointmentList.getItems().get(myIndex).getDate());
                   time.setText(appointmentList.getItems().get(myIndex).getTime());
                   service.setText(appointmentList.getItems().get(myIndex).getService());
                   dentist.setText(appointmentList.getItems().get(myIndex).getDentist());

                           
                         
                           
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
    idclmn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameclmn.setCellValueFactory(new PropertyValueFactory<>("name"));
    dateclmn.setCellValueFactory(new PropertyValueFactory<>("date"));
    timeclmn.setCellValueFactory(new PropertyValueFactory<>("time"));
    serviceclmn.setCellValueFactory(new PropertyValueFactory<>("service"));
    dentistclmn.setCellValueFactory(new PropertyValueFactory<>("dentist"));
    getDBConnection();
    appointmentList();

    

    
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
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentCreate.fxml"));
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









