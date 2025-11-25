package database;

import models.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Mock database for demo without MySQL
public class MockDatabase {
    private static MockDatabase instance;
    
    private Map<Integer, Member> members = new ConcurrentHashMap<>();
    private Map<Integer, Trainer> trainers = new ConcurrentHashMap<>();
    private Map<Integer, Workout> workouts = new ConcurrentHashMap<>();
    private Map<Integer, Challenge> challenges = new ConcurrentHashMap<>();
    private Map<Integer, Reward> rewards = new ConcurrentHashMap<>();
    
    private Map<String, Member> membersByUsername = new ConcurrentHashMap<>();
    
    private AtomicInteger memberIdCounter = new AtomicInteger(1);
    private AtomicInteger trainerIdCounter = new AtomicInteger(1);
    private AtomicInteger workoutIdCounter = new AtomicInteger(1);
    private AtomicInteger challengeIdCounter = new AtomicInteger(1);
    private AtomicInteger rewardIdCounter = new AtomicInteger(1);
    
    private MockDatabase() {
        initializeSampleData();
    }
    
    public static synchronized MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }
    
    private void initializeSampleData() {
        // Sample Members
        Member john = new Member(memberIdCounter.getAndIncrement(), "john_doe", "john@example.com", "password123", "YEARLY");
        john.setLevel(5);
        john.setPoints(450);
        john.setWorkoutsCompleted(23);
        john.setChallengesCompleted(3);
        addMember(john);
        
        Member jane = new Member(memberIdCounter.getAndIncrement(), "jane_smith", "jane@example.com", "password123", "MONTHLY");
        jane.setLevel(3);
        jane.setPoints(280);
        jane.setWorkoutsCompleted(14);
        jane.setChallengesCompleted(2);
        addMember(jane);
        
        Member mike = new Member(memberIdCounter.getAndIncrement(), "mike_wilson", "mike@example.com", "password123", "QUARTERLY");
        mike.setLevel(7);
        mike.setPoints(680);
        mike.setWorkoutsCompleted(34);
        mike.setChallengesCompleted(5);
        addMember(mike);
        
        // Sample Trainers
        Trainer alex = new Trainer(trainerIdCounter.getAndIncrement(), "trainer_alex", "alex@gym.com", "trainer123", "Strength Training", 5);
        alex.setRating(4.8);
        alex.setClientsHandled(45);
        trainers.put(alex.getId(), alex);
        
        // Sample Challenges
        Challenge cardio = new Challenge(challengeIdCounter.getAndIncrement(), "30-Day Cardio Challenge", 
            "Complete 30 cardio workouts in 30 days", "HARD", 30, 500);
        challenges.put(cardio.getId(), cardio);
        
        Challenge strength = new Challenge(challengeIdCounter.getAndIncrement(), "Beginner Strength", 
            "Complete 10 strength training sessions", "EASY", 10, 150);
        challenges.put(strength.getId(), strength);
        
        Challenge calorie = new Challenge(challengeIdCounter.getAndIncrement(), "Calorie Burner", 
            "Burn 5000 calories total", "MEDIUM", 5000, 300);
        challenges.put(calorie.getId(), calorie);
        
        // Sample Rewards
        rewards.put(rewardIdCounter.getAndIncrement(), new Reward(1, "Free Protein Shake", 
            "One free protein shake from the gym bar", 50, "FOOD"));
        rewards.put(rewardIdCounter.getAndIncrement(), new Reward(2, "Personal Training Session", 
            "One free 1-hour personal training session", 200, "SERVICE"));
        rewards.put(rewardIdCounter.getAndIncrement(), new Reward(3, "Gym Merchandise", 
            "Free gym t-shirt or water bottle", 150, "MERCHANDISE"));
        rewards.put(rewardIdCounter.getAndIncrement(), new Reward(4, "Premium Locker", 
            "1 month premium locker access", 300, "SERVICE"));
        rewards.put(rewardIdCounter.getAndIncrement(), new Reward(5, "Massage Session", 
            "30-minute massage therapy session", 400, "SERVICE"));
    }
    
    // Member operations
    public void addMember(Member member) {
        if (member.getId() == 0) {
            member.setId(memberIdCounter.getAndIncrement());
        }
        members.put(member.getId(), member);
        membersByUsername.put(member.getUsername(), member);
    }
    
    public Member getMemberById(int id) {
        return members.get(id);
    }
    
    public Member getMemberByUsername(String username) {
        return membersByUsername.get(username);
    }
    
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }
    
    public void updateMember(Member member) {
        members.put(member.getId(), member);
        membersByUsername.put(member.getUsername(), member);
    }
    
    // Workout operations
    public void addWorkout(Workout workout) {
        if (workout.getId() == 0) {
            workout.setId(workoutIdCounter.getAndIncrement());
        }
        workouts.put(workout.getId(), workout);
    }
    
    public List<Workout> getWorkoutsByMemberId(int memberId) {
        List<Workout> result = new ArrayList<>();
        for (Workout w : workouts.values()) {
            if (w.getMemberId() == memberId) {
                result.add(w);
            }
        }
        return result;
    }
    
    // Challenge operations
    public List<Challenge> getActiveChallenges() {
        List<Challenge> result = new ArrayList<>();
        for (Challenge c : challenges.values()) {
            if (c.isActive()) {
                result.add(c);
            }
        }
        return result;
    }
    
    // Reward operations
    public List<Reward> getAvailableRewards() {
        List<Reward> result = new ArrayList<>();
        for (Reward r : rewards.values()) {
            if (r.isAvailable()) {
                result.add(r);
            }
        }
        return result;
    }
    
    // Trainer operations
    public Trainer getTrainerById(int id) {
        return trainers.get(id);
    }
}
