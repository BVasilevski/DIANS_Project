package mk.ukim.finki.MacedonianWineyardJourney.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The user does not exist");
    }
}
