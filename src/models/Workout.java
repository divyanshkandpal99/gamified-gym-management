package models;

import java.time.LocalDateTime;

public class Workout {
    private int id;
    private int memberId;
    private String workoutType;
    private int duration; // in minutes
    private int caloriesBurned;
    private LocalDateTime completedAt;
    private int pointsEarned;
    
    public Workout() {}
    
    public Workout(int id, int memberId, String workoutType, int duration, int caloriesBurned) {
        this.id = id;
        this.memberId = memberId;
        this.workoutType = workoutType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.completedAt = LocalDateTime.now();
        this.pointsEarned = calculatePoints();
    }
    
    private int calculatePoints() {
        return (duration / 10) + (caloriesBurned / 50);
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    
    public String getWorkoutType() { return workoutType; }
    public void setWorkoutType(String workoutType) { this.workoutType = workoutType; }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public int getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(int caloriesBurned) { this.caloriesBurned = caloriesBurned; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    
    public int getPointsEarned() { return pointsEarned; }
    public void setPointsEarned(int pointsEarned) { this.pointsEarned = pointsEarned; }
}
