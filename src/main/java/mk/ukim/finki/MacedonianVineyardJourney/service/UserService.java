package mk.ukim.finki.MacedonianVineyardJourney.service;

import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;

import java.util.List;

public interface UserService {
    List<Winery> getRecentlyVisitedWineries(String username);

    void addToRecentlyVisited(String username, Long wineryId);

    void save(String username, String password, String name, String surname);

    User login(String username, String password);
}