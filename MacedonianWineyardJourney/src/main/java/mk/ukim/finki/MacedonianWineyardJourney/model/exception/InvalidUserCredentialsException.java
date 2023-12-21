package mk.ukim.finki.MacedonianWineyardJourney.model.exception;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Invalid credentials");
    }
}
