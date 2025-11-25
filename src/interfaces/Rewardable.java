package interfaces;

import models.Member;

// Interface for reward system
public interface Rewardable {
    boolean canRedeem(int userPoints);
    void redeem(Member member);
    int getPointsCost();
}
