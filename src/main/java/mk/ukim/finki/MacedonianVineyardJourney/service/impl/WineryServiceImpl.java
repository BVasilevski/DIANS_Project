package mk.ukim.finki.MacedonianVineyardJourney.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianVineyardJourney.model.exception.InvalidWineryIdException;
import mk.ukim.finki.MacedonianVineyardJourney.repository.UserRepository;
import mk.ukim.finki.MacedonianVineyardJourney.repository.WineryRepository;
import mk.ukim.finki.MacedonianVineyardJourney.service.UserService;
import mk.ukim.finki.MacedonianVineyardJourney.service.WineryService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class WineryServiceImpl implements WineryService {
    private final WineryRepository wineryRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public WineryServiceImpl(WineryRepository repository, UserRepository userRepository, UserService userService) {
        this.wineryRepository = repository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<Winery> findAll() {
        return this.wineryRepository.findAll();
    }

    @Override
    public Optional<Winery> findById(Long id) {
        return this.wineryRepository.findById(id);
    }

    @Override
    public void save(Winery winery) {
        this.wineryRepository.save(winery);
    }

    @Override
    public void delete(Long id) {
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            user.getRecentlyVisited().removeIf(winery -> winery.getId().equals(id));
        }
        Winery winery = this.wineryRepository.findById(id).orElseThrow(InvalidWineryIdException::new);
        this.wineryRepository.delete(winery);
    }

    @Override
    public List<Winery> findAllByNameContains(String wineryName) {
        return this.wineryRepository.findWineriesByNameContainingIgnoreCase(wineryName);
    }

    @Override
    public void showWineryOnMap(Long id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Optional<Winery> winery = findById(id);

        if (winery.isPresent()) {
            model.addAttribute("destinationAddress", winery.get().getMapLocation());
            model.addAttribute("winery", winery.get());

            if (user != null) {
                userService.addToRecentlyVisited(user.getUsername(), winery.get().getId());
            }
        }
    }
}
