package mk.ukim.finki.MacedonianVineyardJourney.model;

import mk.ukim.finki.MacedonianVineyardJourney.model.exception.InvalidUserCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(String username, String password, String name, String surname) {
        if (!username.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
            return new User(username, password, name, surname);
        } else {
            throw new InvalidUserCredentialsException();
        }
    }
}
