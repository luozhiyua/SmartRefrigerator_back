package com.github.angel.raa.modules.service.interfaces;

import com.github.angel.raa.modules.dto.FoodDTO;
import com.github.angel.raa.modules.dto.MenuDTO;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;

import java.util.List;

public interface FoodService {

    List<FoodDTO> getAllFoods();

    List<FoodDTO> getFoodsByDate();

    FoodDTO getFoodItemsById(@NonNull Long id);

    Response addFoodItem(@NonNull FoodDTO body);

    Response editFoodItem(@NonNull Long id, FoodDTO body);

    Response deleteFoodItem(@NonNull Long id);


}