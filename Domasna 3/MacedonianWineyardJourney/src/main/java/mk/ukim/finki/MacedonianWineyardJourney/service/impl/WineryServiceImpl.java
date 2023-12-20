package mk.ukim.finki.MacedonianWineyardJourney.service.impl;

import mk.ukim.finki.MacedonianWineyardJourney.model.User;
import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;
import mk.ukim.finki.MacedonianWineyardJourney.model.exception.InvalidWineryIdException;
import mk.ukim.finki.MacedonianWineyardJourney.repository.UserRepository;
import mk.ukim.finki.MacedonianWineyardJourney.repository.WineryRepository;
import mk.ukim.finki.MacedonianWineyardJourney.service.WineryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineryServiceImpl implements WineryService {
    private final WineryRepository wineryRepository;
    private final UserRepository userRepository;

    public WineryServiceImpl(WineryRepository repository, UserRepository userRepository) {
        this.wineryRepository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Winery> findAll() {
        return this.wineryRepository.findAll();
    }

    @Override
    public List<Winery> findByTown(String town) {
        return this.wineryRepository.findByTown(town);
    }

    @Override
    public List<Winery> findByName(String name) {
        return this.wineryRepository.findByName(name);
    }

    @Override
    public List<Winery> findByActivity(String activity) {
        return this.wineryRepository.findByActivity(activity);
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
        return this.wineryRepository.findAllByNameContains(wineryName);
    }
}
