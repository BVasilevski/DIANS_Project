package mk.ukim.finki.MacedonianVineyardJourney.repository;

import mk.ukim.finki.MacedonianVineyardJourney.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery, Long> {
    List<Winery> findWineriesByNameContainingIgnoreCase(String name);
}
