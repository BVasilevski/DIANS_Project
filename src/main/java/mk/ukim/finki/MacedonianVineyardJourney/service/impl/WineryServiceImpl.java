package mk.ukim.finki.MacedonianVineyardJourney.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianVineyardJourney.model.WineryFactory;
import mk.ukim.finki.MacedonianVineyardJourney.model.exception.InvalidWineryIdException;
import mk.ukim.finki.MacedonianVineyardJourney.repository.UserRepository;
import mk.ukim.finki.MacedonianVineyardJourney.repository.WineryRepository;
import mk.ukim.finki.MacedonianVineyardJourney.service.UserService;
import mk.ukim.finki.MacedonianVineyardJourney.service.WineryService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class WineryServiceImpl implements WineryService {
    private final WineryRepository wineryRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final WineryFactory wineryFactory;

    public WineryServiceImpl(WineryRepository repository, UserService userService, UserRepository userRepository, WineryFactory wineryFactory) {
        this.wineryRepository = repository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.wineryFactory = wineryFactory;
    }

    @Override
    public List<Winery> findAll() {
        return this.wineryRepository.findAll();
    }

    @Override
    public Winery findById(Long id) {
        return this.wineryRepository.findById(id).orElseThrow(InvalidWineryIdException::new);
    }

    @Override
    public void save(String name, String location, String workingHours, String town, String activity, String coordinates) {
        Winery winery = wineryFactory.createWinery(name, location, workingHours, town, activity, coordinates);
        this.wineryRepository.save(winery);
    }

    @Override
    public void delete(Long id) {
        List<User> users = this.userRepository.findAll();
        Winery winery = this.findById(id);

        for (User user : users) {
            user.getRecentlyVisited().removeIf(win -> win.getId().equals(winery.getId()));
        }

        this.wineryRepository.delete(winery);
    }

    @Override
    public List<Winery> findAllByNameContains(String wineryName) {
        return this.wineryRepository.findWineriesByNameContainingIgnoreCase(wineryName);
    }

    @Override
    public void showWineryOnMap(Long id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Winery winery = findById(id);

        model.addAttribute("destinationAddress", winery.getMapLocation());
        model.addAttribute("winery", winery);

        if (user != null) {
            userService.addToRecentlyVisited(user.getUsername(), winery.getId());
        }
    }
}

