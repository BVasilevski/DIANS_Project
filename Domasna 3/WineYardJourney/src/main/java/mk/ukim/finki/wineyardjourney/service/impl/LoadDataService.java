package mk.ukim.finki.wineyardjourney.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mk.ukim.finki.wineyardjourney.model.Winery;
import mk.ukim.finki.wineyardjourney.repository.WineryRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Service for transferring the data from json file to a postgres database, only ran once to migrate the data
 */
@Service
public class LoadDataService {
    private final WineryRepository wineryRepository;

    public LoadDataService(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    public List<Winery> loadDataAndSaveToRepository() throws IOException {
        File file = new File("src/main/resources/data.json");
        List<Winery> wineries;
        Gson gson = new Gson();
        try (Reader reader = new FileReader(file)) {
            Type wineryListType = new TypeToken<List<Winery>>() {
            }.getType();
            wineries = gson.fromJson(reader, wineryListType);
        }
        wineryRepository.saveAll(wineries);
        return wineries;
    }

}
