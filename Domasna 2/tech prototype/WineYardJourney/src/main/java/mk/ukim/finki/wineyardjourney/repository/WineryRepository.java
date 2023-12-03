package mk.ukim.finki.wineyardjourney.repository;

import mk.ukim.finki.wineyardjourney.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery, Long> {
    List<Winery> findByTown(String town);


    List<Winery> findByName(String name);

    List<Winery> findByActivity(String activity);

    Winery save(Winery winery);
}
