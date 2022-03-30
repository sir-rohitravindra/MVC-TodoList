package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Model {

    Connection connection = null;
    Statement stmt = null;

    public void EstablishDBConnection() {
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/todo", "postgres", "admin");
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("Model.EstablishDBConnection() failed");
        }

    }

    public void CloseDBConnection() {
        try {
            connection.close();

        } catch (Exception e) {
            System.err.println("Failed to close connection");
        }

    }

    public String InsertDB(String taskName, String status) {

        try {

            if (connection != null) {

                stmt = connection.createStatement();
                String queryString = "insert into tasks(taskname,status) values('" + taskName + "','" + status + "')";
                stmt.executeUpdate(queryString);
                stmt.close();
                System.out.println("Insert DB succesfull");
                // connection.commit();

            } else {
                System.err.println("Connection Failed!");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    public List<String> FetchData() {
        List<String> Fetched = new ArrayList<String>();
        try {

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks;");
            while (rs.next()) {

                Fetched.add(rs.getString("taskname"));
            }

            return Fetched;

        } catch (Exception e) {

            System.out.println("Model.FetchData() failed");
        }
        return Fetched;

    }

    /*
     * List alist = new ArrayList();
     * alist.add(1);
     * alist.add(2.3) ;
     * alist.add(“Hello”);
     * alist.get(index); to fetch element at index
     * alist.remove(index); to remove element at index
     * to print whole list: SOP(alist.toString()); [1,2.3,”Hello”] printed
     * 
     * 
     */

}
