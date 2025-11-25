package interfaces;

// Interface for achievements and challenges
public interface Achievable {
    boolean isCompleted(int currentProgress);
    int getRewardPoints();
    String getAchievementName();
}
