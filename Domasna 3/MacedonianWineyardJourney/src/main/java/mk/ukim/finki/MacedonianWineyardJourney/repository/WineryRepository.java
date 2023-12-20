package mk.ukim.finki.MacedonianWineyardJourney.repository;

import mk.ukim.finki.MacedonianWineyardJourney.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery, Long> {
    List<Winery> findByTown(String town);

    List<Winery> findByName(String name);

    List<Winery> findByActivity(String activity);

    List<Winery> findAllByNameContains(String name);

    Winery save(Winery winery);

    void deleteById(Long id);
}
