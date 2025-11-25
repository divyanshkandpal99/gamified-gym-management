package models;

import java.io.Serializable;

// Base class demonstrating Inheritance
public abstract class User implements Serializable {
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected int level;
    protected int points;
    
    public User() {}
    
    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.level = 1;
        this.points = 0;
    }
    
    // Abstract method for Polymorphism
    public abstract String getUserType();
    public abstract void displayDashboard();
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    
    public void addPoints(int points) {
        this.points += points;
        checkLevelUp();
    }
    
    private void checkLevelUp() {
        int newLevel = (this.points / 100) + 1;
        if (newLevel > this.level) {
            this.level = newLevel;
        }
    }
}
