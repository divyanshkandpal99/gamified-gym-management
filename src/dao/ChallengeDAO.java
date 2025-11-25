package dao;

import database.DatabaseConnection;
import database.MockDatabase;
import exceptions.DatabaseException;
import models.Challenge;

import java.util.List;

// Database operations class for Challenges
public class ChallengeDAO {
    private MockDatabase db;
    
    public ChallengeDAO() throws DatabaseException {
        DatabaseConnection.getInstance();
        this.db = MockDatabase.getInstance();
    }
    
    public synchronized void addChallenge(Challenge challenge) throws DatabaseException {
        // Not implemented in mock
    }
    
    public List<Challenge> getActiveChallenges() throws DatabaseException {
        return db.getActiveChallenges();
    }
    
    public List<Challenge> getAllChallenges() throws DatabaseException {
        return db.getActiveChallenges();
    }
    
    public synchronized void updateChallenge(Challenge challenge) throws DatabaseException {
        // Not implemented in mock
    }
}
