package mk.ukim.finki.wineyardjourney.service.impl;

import mk.ukim.finki.wineyardjourney.model.User;
import mk.ukim.finki.wineyardjourney.model.Winery;
import mk.ukim.finki.wineyardjourney.repository.UserRepository;
import mk.ukim.finki.wineyardjourney.repository.WineryRepository;
import mk.ukim.finki.wineyardjourney.service.UserService;
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

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }

    @Override
    public List<Winery> getRecentlyVisitedWineries(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            return user.getRecentlyVisited();
        }
        return new ArrayList<>();
    }

    @Override
    public void addToRecentlyVisited(String username, Long wineryId) {
        User user = this.userRepository.findByUsername(username);
        Optional<Winery> winery = this.wineryRepository.findById(wineryId);
        if (user != null && winery.isPresent()) {
            user.getRecentlyVisited().add(winery.get());
        }
    }
}
