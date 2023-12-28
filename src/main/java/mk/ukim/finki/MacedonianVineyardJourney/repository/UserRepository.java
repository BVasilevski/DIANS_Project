package mk.ukim.finki.MacedonianVineyardJourney.repository;

import mk.ukim.finki.MacedonianVineyardJourney.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
