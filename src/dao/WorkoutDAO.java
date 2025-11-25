package dao;

import database.DatabaseConnection;
import database.MockDatabase;
import exceptions.DatabaseException;
import models.Workout;

import java.util.List;

// Database operations class for Workouts
public class WorkoutDAO {
    private MockDatabase db;
    
    public WorkoutDAO() throws DatabaseException {
        DatabaseConnection.getInstance();
        this.db = MockDatabase.getInstance();
    }
    
    public synchronized void addWorkout(Workout workout) throws DatabaseException {
        db.addWorkout(workout);
    }
    
    public List<Workout> getWorkoutsByMemberId(int memberId) throws DatabaseException {
        return db.getWorkoutsByMemberId(memberId);
    }
    
    public List<Workout> getAllWorkouts() throws DatabaseException {
        return db.getWorkoutsByMemberId(0); // Mock doesn't have this
    }
}
