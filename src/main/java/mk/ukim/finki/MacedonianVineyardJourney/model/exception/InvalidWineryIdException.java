package mk.ukim.finki.MacedonianVineyardJourney.model.exception;

public class InvalidWineryIdException extends RuntimeException {
    public InvalidWineryIdException() {
        super("There is no winery with the given id");
    }
}
