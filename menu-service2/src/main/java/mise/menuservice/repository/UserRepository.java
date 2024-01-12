package mise.menuservice.repository;

//import com.github.angel.raa.modules.models.User;
import mise.menuservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
