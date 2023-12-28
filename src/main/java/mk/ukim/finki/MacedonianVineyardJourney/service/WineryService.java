package mk.ukim.finki.MacedonianVineyardJourney.service;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface WineryService {
    List<Winery> findAll();

    Optional<Winery> findById(Long id);

    void save(Winery winery);

    void delete(Long id);

    List<Winery> findAllByNameContains(String wineryName);

    void showWineryOnMap(Long id, Model model, HttpServletRequest request);
}
