package services;

import dao.MemberDAO;
import dao.WorkoutDAO;
import dao.ChallengeDAO;
import exceptions.DatabaseException;
import models.Member;
import models.Workout;
import models.Challenge;

import java.util.List;
import java.util.concurrent.*;

// Multithreading service for gamification operations
public class GamificationService {
    private MemberDAO memberDAO;
    private WorkoutDAO workoutDAO;
    private ChallengeDAO challengeDAO;
    private ExecutorService executorService;
    
    public GamificationService() throws DatabaseException {
        this.memberDAO = new MemberDAO();
        this.workoutDAO = new WorkoutDAO();
        this.challengeDAO = new ChallengeDAO();
        this.executorService = Executors.newFixedThreadPool(5);
    }
    
    // Multithreaded workout processing
    public Future<Boolean> processWorkoutAsync(Member member, Workout workout) {
        return executorService.submit(() -> {
            try {
                synchronized (member) {
                    workoutDAO.addWorkout(workout);
                    member.completeWorkout();
                    member.addPoints(workout.getPointsEarned());
                    memberDAO.updateMember(member);
                }
                System.out.println("Workout processed for " + member.getUsername());
                return true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                return false;
            }
        });
    }
    
    // Synchronized method for challenge completion
    public synchronized void completeChallenge(Member member, Challenge challenge, int currentProgress) throws DatabaseException {
        if (challenge.isCompleted(currentProgress)) {
            member.completeChallenge(challenge.getName());
            member.addPoints(challenge.getRewardPoints());
            memberDAO.updateMember(member);
            System.out.println(member.getUsername() + " completed challenge: " + challenge.getName());
        }
    }
    
    // Multithreaded leaderboard calculation
    public Future<List<Member>> calculateLeaderboardAsync() {
        return executorService.submit(() -> {
            try {
                List<Member> members = memberDAO.getAllMembers();
                members.sort((m1, m2) -> Integer.compare(m2.getPoints(), m1.getPoints()));
                return members;
            } catch (DatabaseException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
    
    // Thread-safe point distribution
    public void distributePointsToMultipleMembers(List<Member> members, int points) {
        CountDownLatch latch = new CountDownLatch(members.size());
        
        for (Member member : members) {
            executorService.submit(() -> {
                try {
                    synchronized (member) {
                        member.addPoints(points);
                        memberDAO.updateMember(member);
                    }
                } catch (DatabaseException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        
        try {
            latch.await();
            System.out.println("Points distributed to all members");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
