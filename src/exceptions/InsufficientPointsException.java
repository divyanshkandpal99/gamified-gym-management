package exceptions;

// Custom exception for reward redemption
public class InsufficientPointsException extends Exception {
    public InsufficientPointsException(String message) {
        super(message);
    }
}
