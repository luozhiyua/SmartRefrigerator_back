package com.github.angel.raa.modules.service.interfaces;

import com.github.angel.raa.modules.dto.FoodDTO;
import com.github.angel.raa.modules.dto.MenuDTO;
import com.github.angel.raa.modules.models.Category;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface FoodService {

    List<FoodDTO> getAllFoods(Long userId);

    List<FoodDTO> getFoodsByDate(Long userId);

    List<FoodDTO> getFoodsByName(Long userId);

    List<FoodDTO> getFoodsByCategory(Category category, Long userId);

    List<FoodDTO> getFoodByInput(String input, Long userId) throws UnsupportedEncodingException;

    FoodDTO getFoodItemsById(@NonNull Long id);

    Response addFoodItem(@NonNull FoodDTO body);

    Response editFoodItem(@NonNull Long id, FoodDTO body);

    Response deleteFoodItem(@NonNull Long id);


}
