package mk.ukim.finki.wineyardjourney.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The user does not exist");
    }
}
