package mk.ukim.finki.MacedonianWineyardJourney.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Winery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @SerializedName("Име")
    private String name;
    @SerializedName("Локација")
    private String location;
    @SerializedName("Град")
    private String town;
    @SerializedName("Работно време")
    private String workingHours;
    @SerializedName("Дејност")
    private String activity;

    @SerializedName("Локација Мапа")
    private String mapLocation;
    @ManyToMany(mappedBy = "recentlyVisited")
    private List<User> visitors;

    public Winery(String name, String location, String town, String workingHours, String activity, String mapLocation) {
        this.name = name;
        this.location = location;
        this.town = town;
        this.workingHours = workingHours;
        this.activity = activity;
        this.mapLocation = mapLocation;
        this.visitors = new ArrayList<>();
    }
}
