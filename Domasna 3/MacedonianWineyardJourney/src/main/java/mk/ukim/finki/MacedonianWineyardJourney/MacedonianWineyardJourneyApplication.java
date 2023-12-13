package mk.ukim.finki.MacedonianWineyardJourney;

import mk.ukim.finki.MacedonianWineyardJourney.service.impl.LoadDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MacedonianWineyardJourneyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MacedonianWineyardJourneyApplication.class, args);
    }

    /**
     * The code below is for reading the json database and mapping it to objects.
     * When we will use postgres database the code will be commented because the database will be persistent and it won't need to be written to on every app startup.
     */
    private final LoadDataService loadDataService;

    public MacedonianWineyardJourneyApplication(LoadDataService loadDataService) {
        this.loadDataService = loadDataService;
    }


//    @Override
//    public void run(String... args) throws Exception {
//        List<Winery> data = loadDataService.loadDataAndSaveToRepository();
//    }

}
