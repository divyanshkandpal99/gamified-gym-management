package dao;

import database.DatabaseConnection;
import exceptions.DatabaseException;
import exceptions.UserNotFoundException;
import models.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Database operations class for Trainers
public class TrainerDAO {
    private Connection connection;
    
    public TrainerDAO() throws DatabaseException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    public synchronized void addTrainer(Trainer trainer) throws DatabaseException {
        String sql = "INSERT INTO trainers (username, email, password, specialization, years_of_experience, rating, clients_handled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trainer.getUsername());
            stmt.setString(2, trainer.getEmail());
            stmt.setString(3, trainer.getPassword());
            stmt.setString(4, trainer.getSpecialization());
            stmt.setInt(5, trainer.getYearsOfExperience());
            stmt.setDouble(6, trainer.getRating());
            stmt.setInt(7, trainer.getClientsHandled());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        trainer.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error adding trainer", e);
        }
    }
    
    public Trainer getTrainerById(int id) throws DatabaseException, UserNotFoundException {
        String sql = "SELECT * FROM trainers WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractTrainerFromResultSet(rs);
            } else {
                throw new UserNotFoundException("Trainer with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving trainer", e);
        }
    }
    
    public List<Trainer> getAllTrainers() throws DatabaseException {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainers";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                trainers.add(extractTrainerFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving all trainers", e);
        }
        
        return trainers;
    }
    
    public synchronized void updateTrainer(Trainer trainer) throws DatabaseException {
        String sql = "UPDATE trainers SET username = ?, email = ?, password = ?, specialization = ?, years_of_experience = ?, rating = ?, clients_handled = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, trainer.getUsername());
            stmt.setString(2, trainer.getEmail());
            stmt.setString(3, trainer.getPassword());
            stmt.setString(4, trainer.getSpecialization());
            stmt.setInt(5, trainer.getYearsOfExperience());
            stmt.setDouble(6, trainer.getRating());
            stmt.setInt(7, trainer.getClientsHandled());
            stmt.setInt(8, trainer.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating trainer", e);
        }
    }
    
    public synchronized void deleteTrainer(int id) throws DatabaseException {
        String sql = "DELETE FROM trainers WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting trainer", e);
        }
    }
    
    private Trainer extractTrainerFromResultSet(ResultSet rs) throws SQLException {
        Trainer trainer = new Trainer();
        trainer.setId(rs.getInt("id"));
        trainer.setUsername(rs.getString("username"));
        trainer.setEmail(rs.getString("email"));
        trainer.setPassword(rs.getString("password"));
        trainer.setSpecialization(rs.getString("specialization"));
        trainer.setYearsOfExperience(rs.getInt("years_of_experience"));
        trainer.setRating(rs.getDouble("rating"));
        trainer.setClientsHandled(rs.getInt("clients_handled"));
        return trainer;
    }
}
