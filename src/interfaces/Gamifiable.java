package interfaces;

// Interface for gamification features
public interface Gamifiable {
    void earnPoints(int points);
    void completeChallenge(String challengeName);
    int getCurrentLevel();
    void levelUp();
}
