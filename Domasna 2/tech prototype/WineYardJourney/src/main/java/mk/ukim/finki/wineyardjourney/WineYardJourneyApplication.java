package mk.ukim.finki.wineyardjourney;

import mk.ukim.finki.wineyardjourney.model.Winery;
import mk.ukim.finki.wineyardjourney.service.impl.LoadDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WineYardJourneyApplication implements CommandLineRunner {

    private final LoadDataService loadDataService;

    public WineYardJourneyApplication(LoadDataService loadDataService) {
        this.loadDataService = loadDataService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WineYardJourneyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Winery> data = loadDataService.loadDataAndSaveToRepository("path/to/your/json/file.json");
    }
}
