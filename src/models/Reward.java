package models;

import interfaces.Rewardable;

public class Reward implements Rewardable {
    private int id;
    private String name;
    private String description;
    private int pointsCost;
    private String category;
    private boolean isAvailable;
    
    public Reward() {}
    
    public Reward(int id, String name, String description, int pointsCost, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pointsCost = pointsCost;
        this.category = category;
        this.isAvailable = true;
    }
    
    @Override
    public boolean canRedeem(int userPoints) {
        return userPoints >= pointsCost && isAvailable;
    }
    
    @Override
    public void redeem(Member member) {
        if (canRedeem(member.getPoints())) {
            member.setPoints(member.getPoints() - pointsCost);
            System.out.println("Reward redeemed: " + name);
        }
    }
    
    @Override
    public int getPointsCost() {
        return this.pointsCost;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public void setPointsCost(int pointsCost) { this.pointsCost = pointsCost; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
