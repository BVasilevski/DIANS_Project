package mk.ukim.finki.MacedonianVineyardJourney.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The user does not exist");
    }
}
