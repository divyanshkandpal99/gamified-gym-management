package models;

import interfaces.Achievable;
import java.time.LocalDate;

public class Challenge implements Achievable {
    private int id;
    private String name;
    private String description;
    private String difficulty;
    private int targetValue;
    private int rewardPoints;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    
    public Challenge() {}
    
    public Challenge(int id, String name, String description, String difficulty, int targetValue, int rewardPoints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.targetValue = targetValue;
        this.rewardPoints = rewardPoints;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusDays(30);
        this.isActive = true;
    }
    
    @Override
    public boolean isCompleted(int currentProgress) {
        return currentProgress >= targetValue;
    }
    
    @Override
    public int getRewardPoints() {
        return this.rewardPoints;
    }
    
    @Override
    public String getAchievementName() {
        return this.name;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    
    public int getTargetValue() { return targetValue; }
    public void setTargetValue(int targetValue) { this.targetValue = targetValue; }
    
    public void setRewardPoints(int rewardPoints) { this.rewardPoints = rewardPoints; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
