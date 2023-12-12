package mk.ukim.finki.wineyardjourney.service.impl;

import mk.ukim.finki.wineyardjourney.model.Winery;
import mk.ukim.finki.wineyardjourney.repository.WineryRepository;
import mk.ukim.finki.wineyardjourney.service.WineryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineryServiceImpl implements WineryService {
    private final WineryRepository repository;

    public WineryServiceImpl(WineryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Winery> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Winery> findByTown(String town) {
        return this.repository.findByTown(town);
    }

    @Override
    public List<Winery> findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public List<Winery> findByActivity(String activity) {
        return this.repository.findByActivity(activity);
    }

    @Override
    public Optional<Winery> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void save(Winery winery) {
        this.repository.save(winery);
    }
}
