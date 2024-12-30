package university.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    public Connection connection;
    public Statement statement;

    public Conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection("jdbc:mysql:///Universitymanagement", "root", "Aman@2002");

            // Create a statement
            statement = connection.createStatement();

        } catch (Exception e) {
            // Print error details to help with debugging
            e.printStackTrace();
        }
    }

    // Getter for connection (optional)
    public Connection getConnection() {
        return connection;
    }

    // Getter for statement (optional)
    public Statement getStatement() {
        return statement;
    }
}
