package mk.ukim.finki.MacedonianWineyardJourney.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app-users")
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Winery> recentlyVisited;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.ROLE_USER;
        this.recentlyVisited = new ArrayList<>();
    }
}
