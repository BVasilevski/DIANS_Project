package mk.ukim.finki.wineyardjourney;

import mk.ukim.finki.wineyardjourney.service.impl.LoadDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import mk.ukim.finki.wineyardjourney.model.Winery;

import java.util.List;

@SpringBootApplication
public class WineYardJourneyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WineYardJourneyApplication.class, args);
    }

    /**
     * The code below is for reading the json database and mapping it to objects.
     * When we will use postgres database the code will be commented because the database will be persistent and it won't need to be written to on every app startup.
     */
    private final LoadDataService loadDataService;

    public WineYardJourneyApplication(LoadDataService loadDataService) {
        this.loadDataService = loadDataService;
    }


//    @Override
//    public void run(String... args) throws Exception {
//        List<Winery> data = loadDataService.loadDataAndSaveToRepository();
//    }

}
