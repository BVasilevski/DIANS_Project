package mk.ukim.finki.wineyardjourney.service;

import mk.ukim.finki.wineyardjourney.model.User;
import mk.ukim.finki.wineyardjourney.model.Winery;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Winery> getRecentlyVisitedWineries(String username);
    void addToRecentlyVisited(String username, Long wineryId);

    void save(User user);

    boolean login(String username, String password);


    User findByUsername(String username);
}
