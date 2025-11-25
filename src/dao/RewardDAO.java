package dao;

import database.DatabaseConnection;
import database.MockDatabase;
import exceptions.DatabaseException;
import models.Reward;

import java.util.List;

// Database operations class for Rewards
public class RewardDAO {
    private MockDatabase db;
    
    public RewardDAO() throws DatabaseException {
        DatabaseConnection.getInstance();
        this.db = MockDatabase.getInstance();
    }
    
    public synchronized void addReward(Reward reward) throws DatabaseException {
        // Not implemented in mock
    }
    
    public List<Reward> getAvailableRewards() throws DatabaseException {
        return db.getAvailableRewards();
    }
    
    public List<Reward> getAllRewards() throws DatabaseException {
        return db.getAvailableRewards();
    }
    
    public synchronized void updateReward(Reward reward) throws DatabaseException {
        // Not implemented in mock
    }
}
