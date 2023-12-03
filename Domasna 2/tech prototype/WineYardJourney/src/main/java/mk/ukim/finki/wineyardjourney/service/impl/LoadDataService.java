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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LoadDataService {
    private final ObjectMapper objectMapper;
    private final WineryRepository wineryRepository;

    public LoadDataService(ObjectMapper objectMapper, WineryRepository wineryRepository) {
        this.objectMapper = objectMapper;
        this.wineryRepository = wineryRepository;
    }

    public List<Winery> loadDataAndSaveToRepository(String filePath) throws IOException {
        File file = new File("D:\\Fakultet\\III Godina\\V Semestar\\Dians\\DIANS_Project\\DIANS_Project\\Domasna 1\\Data\\data.json");
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
