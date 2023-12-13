package mk.ukim.finki.MacedonianWineyardJourney.service;

import mk.ukim.finki.MacedonianWineyardJourney.model.User;
import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Winery> getRecentlyVisitedWineries(String username);
    void addToRecentlyVisited(String username, Long wineryId);

    void save(User user);

    User login(String username, String password);


    Optional<User> findByUsername(String username);
}
