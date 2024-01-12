package mise.menuservice.repository;

//import com.github.angel.raa.modules.models.Food;
import mise.menuservice.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>{ }
