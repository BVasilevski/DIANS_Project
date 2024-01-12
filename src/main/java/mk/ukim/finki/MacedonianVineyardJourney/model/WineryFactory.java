package mk.ukim.finki.MacedonianVineyardJourney.model;

import org.springframework.stereotype.Component;

@Component
public class WineryFactory {
    public Winery createWinery(String name, String location, String town, String workingHours, String activity, String mapLocation) {
        return new Winery(name, location, town, workingHours, activity, mapLocation);
    }
}
