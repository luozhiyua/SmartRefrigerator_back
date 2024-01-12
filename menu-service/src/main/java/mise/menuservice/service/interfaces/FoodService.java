package mise.menuservice.service.interfaces;


import lombok.NonNull;
import mise.menuservice.dto.FoodDTO;
import mise.menuservice.models.Category;
import mise.menuservice.utils.Response;

//import javax.xml.ws.Response;

import java.util.List;

public interface FoodService {

    List<FoodDTO> getAllFoods(Long userId);

    List<FoodDTO> getFoodsByDate(Long userId);

    List<FoodDTO> getFoodsByName(Long userId);

    List<FoodDTO> getFoodsByCategory(Category category, Long userId);

    List<FoodDTO> getFoodByInput(String input, Long userId);

    FoodDTO getFoodItemsById(@NonNull Long id);

    Response addFoodItem(@NonNull FoodDTO body);

    Response editFoodItem(@NonNull Long id, FoodDTO body);

    Response deleteFoodItem(@NonNull Long id);


}
