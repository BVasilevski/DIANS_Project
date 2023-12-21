package mk.ukim.finki.MacedonianWineyardJourney.service;

import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;

import java.util.List;
import java.util.Optional;

public interface WineryService {
    List<Winery> findAll();

    Optional<Winery> findById(Long id);

    void save(Winery winery);

    void delete(Long id);

    List<Winery> findAllByNameContains(String wineryName);
}
