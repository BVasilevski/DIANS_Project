package mk.ukim.finki.MacedonianWineyardJourney.model.exception;

public class InvalidWineryIdException extends RuntimeException {
    public InvalidWineryIdException() {
        super("There is no winery with the given id");
    }
}
