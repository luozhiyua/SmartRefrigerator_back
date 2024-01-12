package mise.menuservice.repository;

//import com.github.angel.raa.modules.models.Menu;
import mise.menuservice.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> { }
