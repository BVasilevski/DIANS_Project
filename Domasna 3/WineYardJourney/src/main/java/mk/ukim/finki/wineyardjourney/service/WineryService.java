package mk.ukim.finki.wineyardjourney.service;

import mk.ukim.finki.wineyardjourney.model.Winery;

import java.util.List;
import java.util.Optional;

public interface WineryService {
    public List<Winery> findAll();

    public List<Winery> findByTown(String town);

    public List<Winery> findByName(String name);

    public List<Winery> findByActivity(String activity);

    Optional<Winery> findById(Long id);

    void save(Winery winery);
}
