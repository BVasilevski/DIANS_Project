package mk.ukim.finki.MacedonianVineyardJourney.model.exception;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Invalid credentials");
    }
}
