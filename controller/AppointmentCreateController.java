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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

    
public class AppointmentCreateController implements Initializable{

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
     @FXML
    private DatePicker calendar;

    @FXML
    private ChoiceBox<String> choicebox1;

    @FXML
    private ChoiceBox<String> choicebox2;

    int id;

    @FXML
    private TextField name, time;
    @FXML


    public void create(ActionEvent event) throws IOException  {
        LocalDate date = calendar.getValue();
        String myFormattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        PreparedStatement pst;
        Connection con = getDBConnection();

        String dburl = "jdbc:mysql://localhost:3306/fencdb";
        String userName = "root";
        String password = "esfenada";

        try
        {
            con = DriverManager.getConnection(dburl, userName, password);
            con.setAutoCommit(true);

        } catch (Exception e){
            e.printStackTrace();
        }
        // fetchAndSetDataForChoiceBox1("SELECT * service_name FROM tbl_services", choicebox1);

    // Fetch and set data for choicebox2 (dentists) from the database
    // fetchAndSetDataForChoiceBox2("SELECT * dentist_name FROM tbl_dentists", choicebox2);

        String patient_name, service_name, dentist_name, appointment_date, appointment_time;

            patient_name = name.getText();
            appointment_date = myFormattedDate;
            appointment_time = time.getText();
            service_name = choicebox1.getValue();
            dentist_name = choicebox2.getValue();
            
        try 
        {
            pst = con.prepareStatement("insert into tbl_appointments(patient_name, appointment_date, appointment_time, service_name, dentist_name) values (?,?,?,?,?)");
            pst.setString(1, patient_name);
            pst.setString(2, myFormattedDate);
            pst.setString(3, appointment_time);
            pst.setString(4, service_name);
            pst.setString(5, dentist_name);
            System.out.println("SQL Query: " + pst.toString()); // Print the SQL query for debugging

            pst.executeUpdate();
          
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dental Appointment");
        
        alert.setHeaderText("Dental Appointment");
        alert.setContentText("Booked Successfully!!");
        alert.showAndWait();
        appointmentList();
            
            name.setText("");
            time.setText("");
           
            

            name.requestFocus();
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    public void delete(ActionEvent event) {
        LocalDate date = calendar.getValue();
        String myFormattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        PreparedStatement pst;
        int myIndex;
        Connection con = getDBConnection();

        myIndex = appointmentList.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(appointmentList.getItems().get(myIndex).getId()));
                     
        try 
        {
            pst = con.prepareStatement("delete from tbl_appointments where appointment_id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dental Appointment");
        
           alert.setHeaderText("Dental Appointment");
           alert.setContentText("Appointment Successfully Deleted!");
           alert.showAndWait();

        appointmentList();
            name.setText("");
            time.setText("");
            
            
        } 
        catch (SQLException ex)
        {
            java.util.logging.Logger.getLogger(AppointmentCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void update(ActionEvent event) {
        LocalDate date = calendar.getValue();
        String myFormattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        PreparedStatement pst;
        Connection con = getDBConnection();
        int myIndex;
        myIndex = appointmentList.getSelectionModel().getSelectedIndex();
         
        id = Integer.parseInt(String.valueOf(appointmentList.getItems().get(myIndex).getId()));

        String patient_name, service_name, dentist_name, appointment_date, appointment_time;
        int appointment_id;

            patient_name = name.getText();
            appointment_date = myFormattedDate;            
            appointment_time = time.getText();
            service_name = choicebox1.getValue();
            dentist_name = choicebox2.getValue();

       try 
       {
           pst = con.prepareStatement("update tbl_appointments set patient_name = ?, appointment_date = ?, appointment_time = ?, service_name = ?, dentist_name = ? where appointment_id = ?"); 
    
           pst.setString(1, patient_name);
           pst.setString(2, myFormattedDate);
           pst.setString(3, appointment_time);
           pst.setString(4, service_name);
           pst.setString(5, dentist_name);
           pst.setInt(6, id);
           System.out.println("SQL Query: " + pst.toString()); // Print the SQL query for debugging

           pst.executeUpdate();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Dental Appointment");
        
           alert.setHeaderText("Dental Appointment");
           alert.setContentText("Appointment Updated!");
           alert.showAndWait();

           appointmentList();
            name.setText("");
            time.setText("");            
           
           

        }
                 
        
       
       catch (SQLException ex) 
       {
        java.util.logging.Logger.getLogger(AppointmentCreateController.class.getName()).log(Level.SEVERE, null, ex);
    }


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
        java.util.logging.Logger.getLogger(AppointmentCreateController.class.getName()).log(Level.SEVERE, null, ex);
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
                   time.setText(appointmentList.getItems().get(myIndex).getTime());
                        
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
    // this.calendar = new DatePicker();
    String[] service = {"Dental Checkup", "Prophylaxis", "Dental Fillings", "Orthodontics", "Implant", "Extraction", "Dental Crown", "Root Canal"};
    String[] dentist = {"Dr. Dhan Lloyd Ferrer", "Dr. Francis Earl Celada", "Dra. Anthonette Mae Navarro", "Dra. Katherine Espinosa"};

    choicebox1.getItems().addAll(service);
    choicebox2.getItems().addAll(dentist);

    

    // Initialize table columns
    // idclmn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameclmn.setCellValueFactory(new PropertyValueFactory<>("name"));
    dateclmn.setCellValueFactory(new PropertyValueFactory<>("date"));
    timeclmn.setCellValueFactory(new PropertyValueFactory<>("time"));
    serviceclmn.setCellValueFactory(new PropertyValueFactory<>("service"));
    dentistclmn.setCellValueFactory(new PropertyValueFactory<>("dentist"));

    // fetchAndSetDataForChoiceBox1("SELECT * service_name FROM tbl_services", choicebox1);

    // // Fetch and set data for choicebox2 (dentists) from the database
    // fetchAndSetDataForChoiceBox2("SELECT * dentist_name FROM tbl_dentists", choicebox2);

    getDBConnection();
    appointmentList();
}


// private void fetchAndSetDataForChoiceBox1(String query, ChoiceBox<String> choiceBox1) {
//     try (Connection connection = getDBConnection();
//          PreparedStatement pst = connection.prepareStatement(query);
//          ResultSet rs = pst.executeQuery()) {

//         ObservableList<String> items = FXCollections.observableArrayList();
//         while (rs.next()) {
//             items.add(rs.getString(1));
//         }

//         choiceBox1.setItems(items);

//     } catch (SQLException e) {
//         e.printStackTrace(); }
//     }
//     private void fetchAndSetDataForChoiceBox2(String query, ChoiceBox<String> choiceBox2) {
//         try (Connection connection = getDBConnection();
//              PreparedStatement pst = connection.prepareStatement(query);
//              ResultSet rs = pst.executeQuery()) {
    
//             ObservableList<String> items = FXCollections.observableArrayList();
//             while (rs.next()) {
//                 items.add(rs.getString(1));
//             }
    
//             choiceBox2.setItems(items);
    
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
// }
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









