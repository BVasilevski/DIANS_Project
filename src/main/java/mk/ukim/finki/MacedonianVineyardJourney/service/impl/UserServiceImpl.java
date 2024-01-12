package mk.ukim.finki.MacedonianVineyardJourney.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.UserFactory;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianVineyardJourney.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.MacedonianVineyardJourney.model.exception.InvalidWineryIdException;
import mk.ukim.finki.MacedonianVineyardJourney.model.exception.UserNotFoundException;
import mk.ukim.finki.MacedonianVineyardJourney.repository.UserRepository;
import mk.ukim.finki.MacedonianVineyardJourney.repository.WineryRepository;
import mk.ukim.finki.MacedonianVineyardJourney.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final WineryRepository wineryRepository;
    private final UserFactory userFactory;

    public UserServiceImpl(UserRepository userRepository, WineryRepository wineryRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.wineryRepository = wineryRepository;
        this.userFactory = userFactory;
    }

    @Override
    public List<Winery> getRecentlyVisitedWineries(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return user.getRecentlyVisited();
    }

    @Override
    @Transactional
    public void addToRecentlyVisited(String username, Long wineryId) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Winery winery = wineryRepository.findById(wineryId).orElseThrow(InvalidWineryIdException::new);

        List<Winery> recentlyVisited = user.getRecentlyVisited();

        boolean wineryExists = recentlyVisited.stream()
                .anyMatch(w -> w.getId().equals(wineryId));

        if (wineryExists) {
            recentlyVisited.removeIf(w -> w.getId().equals(wineryId));
        }

        List<Winery> tempRecentlyVisited = new ArrayList<>(recentlyVisited);
        if (!tempRecentlyVisited.isEmpty() && tempRecentlyVisited.size() >= 4) {
            tempRecentlyVisited.remove(0);
        }

        tempRecentlyVisited.add(winery);

        user.getRecentlyVisited().clear();
        user.getRecentlyVisited().addAll(tempRecentlyVisited);
    }

    @Override
    public void save(String username, String password, String name, String surname) {
        User user = userFactory.createUser(username, password, name, surname);
        userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
