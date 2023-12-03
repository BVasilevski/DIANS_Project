package mk.ukim.finki.wineyardjourney.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    public Winery(String name, String location, String town, String workingHours, String activity) {
        this.name = name;
        this.location = location;
        this.town = town;
        this.workingHours = workingHours;
        this.activity = activity;
    }
}
