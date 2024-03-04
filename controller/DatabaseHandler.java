package controller;

import java.sql.*;

public class DatabaseHandler {

    private static DatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection()
    {
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

        return connection;
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }


    public static boolean validateLogin(String admin_username, String admin_password) {
        String query = "SELECT * FROM tbl_admins WHERE admin_username = ? AND admin_password = ?";
        
        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, admin_username);
            preparedStatement.setString(2, admin_password);

            ResultSet result = preparedStatement.executeQuery();
            
            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}