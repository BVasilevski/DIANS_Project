package mk.ukim.finki.MacedonianVineyardJourney.service;

import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Winery> getRecentlyVisitedWineriesForUserWithUsername(String username);

    void addToRecentlyVisited(String username, Long wineryId);

    void register(String username, String password, String name, String surname);

    User login(String username, String password);

    User findByUsername(String username);
}
