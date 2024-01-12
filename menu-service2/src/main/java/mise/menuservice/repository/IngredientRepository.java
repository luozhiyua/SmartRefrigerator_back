package mise.menuservice.repository;

//import com.github.angel.raa.modules.models.Ingredient;
import mise.menuservice.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
