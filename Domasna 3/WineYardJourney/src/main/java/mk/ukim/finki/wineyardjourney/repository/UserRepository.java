package mk.ukim.finki.wineyardjourney.repository;

import mk.ukim.finki.wineyardjourney.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
