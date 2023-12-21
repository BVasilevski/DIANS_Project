package mk.ukim.finki.MacedonianWineyardJourney.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.MacedonianWineyardJourney.model.User;
import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianWineyardJourney.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.MacedonianWineyardJourney.model.exception.UserNotFoundException;
import mk.ukim.finki.MacedonianWineyardJourney.repository.UserRepository;
import mk.ukim.finki.MacedonianWineyardJourney.repository.WineryRepository;
import mk.ukim.finki.MacedonianWineyardJourney.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WineryRepository wineryRepository;

    public UserServiceImpl(UserRepository userRepository, WineryRepository wineryRepository) {
        this.userRepository = userRepository;
        this.wineryRepository = wineryRepository;
    }
    @Override
    public List<Winery> getRecentlyVisitedWineries(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        if (user != null) {
            return user.getRecentlyVisited();
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public void addToRecentlyVisited(String username, Long wineryId) {
        User user = userRepository.findByUsername(username).orElse(null);
        Optional<Winery> winery = wineryRepository.findById(wineryId);

        if (winery.isPresent() && user != null) {
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

            tempRecentlyVisited.add(winery.get());

            user.getRecentlyVisited().clear();
            user.getRecentlyVisited().addAll(tempRecentlyVisited);
        }
    }


    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
