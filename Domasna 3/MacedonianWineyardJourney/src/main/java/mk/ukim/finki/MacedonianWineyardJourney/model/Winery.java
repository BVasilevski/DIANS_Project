package mk.ukim.finki.MacedonianWineyardJourney.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
}
