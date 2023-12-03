package mk.ukim.finki.wineyardjourney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WineYardJourneyApplication {

    /**
     * The commented code below is ran only once to migrate the json database to postgres database
     */
//    private final LoadDataService loadDataService;
//
//    public WineYardJourneyApplication(LoadDataService loadDataService) {
//        this.loadDataService = loadDataService;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<Winery> data = loadDataService.loadDataAndSaveToRepository();
//    }
    public static void main(String[] args) {
        SpringApplication.run(WineYardJourneyApplication.class, args);
    }
}
