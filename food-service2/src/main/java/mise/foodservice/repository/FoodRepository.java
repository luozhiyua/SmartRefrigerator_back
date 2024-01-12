package mise.foodservice.repository;


import mise.foodservice.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>{ }
