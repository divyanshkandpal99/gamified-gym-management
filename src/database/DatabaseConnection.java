package database;

import exceptions.DatabaseException;
import java.sql.Connection;

// JDBC Database Connectivity - Singleton Pattern
// NOTE: Using MockDatabase for demo. For production, uncomment JDBC code and add MySQL driver.
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static final boolean USE_MOCK = true; // Set to false when MySQL is available
    
    private DatabaseConnection() throws DatabaseException {
        if (USE_MOCK) {
            System.out.println("Using Mock Database (Demo Mode)");
            System.out.println("To use MySQL: Set USE_MOCK=false and add MySQL JDBC driver");
        } else {
            // Uncomment for real MySQL connection
            /*
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gym_management", 
                    "root", 
                    "password"
                );
                System.out.println("Database connected successfully!");
            } catch (ClassNotFoundException e) {
                throw new DatabaseException("MySQL JDBC Driver not found", e);
            } catch (SQLException e) {
                throw new DatabaseException("Failed to connect to database", e);
            }
            */
        }
    }
    
    public static synchronized DatabaseConnection getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return null; // Mock mode doesn't use Connection
    }
    
    public void closeConnection() {
        System.out.println("Database connection closed.");
    }
}
