package models;

import interfaces.Gamifiable;
import java.time.LocalDate;

// Inheritance and Interface implementation
public class Member extends User implements Gamifiable {
    private String membershipType;
    private LocalDate joinDate;
    private LocalDate expiryDate;
    private int workoutsCompleted;
    private int challengesCompleted;
    
    public Member() {
        super();
    }
    
    public Member(int id, String username, String email, String password, String membershipType) {
        super(id, username, email, password);
        this.membershipType = membershipType;
        this.joinDate = LocalDate.now();
        this.expiryDate = joinDate.plusMonths(getMembershipDuration());
        this.workoutsCompleted = 0;
        this.challengesCompleted = 0;
    }
    
    @Override
    public String getUserType() {
        return "MEMBER";
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("=== Member Dashboard ===");
        System.out.println("Username: " + username);
        System.out.println("Level: " + level);
        System.out.println("Points: " + points);
        System.out.println("Workouts: " + workoutsCompleted);
        System.out.println("Challenges: " + challengesCompleted);
    }
    
    @Override
    public void earnPoints(int points) {
        addPoints(points);
    }
    
    @Override
    public void completeChallenge(String challengeName) {
        this.challengesCompleted++;
        addPoints(50);
    }
    
    @Override
    public int getCurrentLevel() {
        return this.level;
    }
    
    @Override
    public void levelUp() {
        this.level++;
    }
    
    private int getMembershipDuration() {
        switch (membershipType.toUpperCase()) {
            case "MONTHLY": return 1;
            case "QUARTERLY": return 3;
            case "YEARLY": return 12;
            default: return 1;
        }
    }
    
    public void completeWorkout() {
        this.workoutsCompleted++;
        addPoints(20);
    }
    
    // Getters and Setters
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    public int getWorkoutsCompleted() { return workoutsCompleted; }
    public void setWorkoutsCompleted(int workoutsCompleted) { this.workoutsCompleted = workoutsCompleted; }
    
    public int getChallengesCompleted() { return challengesCompleted; }
    public void setChallengesCompleted(int challengesCompleted) { this.challengesCompleted = challengesCompleted; }
}
