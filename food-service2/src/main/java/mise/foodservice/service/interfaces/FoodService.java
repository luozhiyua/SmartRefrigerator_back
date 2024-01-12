package mise.foodservice.service.interfaces;


import lombok.NonNull;
import mise.foodservice.dto.FoodDTO;
import mise.foodservice.dto.InputDTO;
import mise.foodservice.models.Category;
import mise.foodservice.utils.Response;

import java.util.List;

public interface FoodService {

    List<FoodDTO> getAllFoods(Long userId);

    List<FoodDTO> getFoodsByDate(Long userId);

    List<FoodDTO> getFoodsByName(Long userId);

    List<FoodDTO> getFoodsByCategory(Category category, Long userId);

    List<FoodDTO> getFoodByInput(InputDTO input, Long userId);

    FoodDTO getFoodItemsById(@NonNull Long id);

    Response addFoodItem(@NonNull FoodDTO body);

    Response editFoodItem(@NonNull Long id, FoodDTO body);

    Response deleteFoodItem(@NonNull Long id);


}
