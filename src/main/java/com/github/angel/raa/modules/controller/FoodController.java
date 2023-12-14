package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.dto.FoodDTO;
import com.github.angel.raa.modules.models.Category;
import com.github.angel.raa.modules.service.interfaces.FoodService;
import com.github.angel.raa.modules.utils.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FoodController {
    private final FoodService foodService;
    @GetMapping("/foods-by-date/{userId}")
    public ResponseEntity<List<FoodDTO>> getFoodsByDate(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(foodService.getFoodsByDate(userId));
    }

    @GetMapping("/foods-by-name/{userId}")
    public ResponseEntity<List<FoodDTO>> getFoodsByName(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(foodService.getFoodsByName(userId));
    }

    //根据食物类型展示
    @GetMapping("/foods-by-category/{category}/{userId}")
    public ResponseEntity<List<FoodDTO>> getFoodsBtCategory(@PathVariable(value = "category") Category category, @PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(foodService.getFoodsByCategory(category, userId));
    }

    //模糊搜索
    @GetMapping("/foods-by-input/{input}/{userId}")
    public ResponseEntity<List<FoodDTO>> getFoodsByInput(@PathVariable(value = "input") String input, @PathVariable(value = "userId") Long userId) throws UnsupportedEncodingException {
        return ResponseEntity.ok(foodService.getFoodByInput(input, userId));
    }

    @GetMapping("/food-by/{foodId}")
    public ResponseEntity<FoodDTO> getFoodById(@Valid @PathVariable(value = "foodId")@Min(1) Long foodId){
        return ResponseEntity.ok(foodService.getFoodItemsById(foodId));
    }
    @PostMapping("/add-food")
    public ResponseEntity<Response> addFoodItem(@Valid @RequestBody FoodDTO food){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.addFoodItem(food));
    }
    @PutMapping("/edit-food/{foodId}")
    public ResponseEntity<Response> editFoodItem(@Valid @RequestBody FoodDTO food, @PathVariable Long foodId){
        return ResponseEntity.ok(foodService.editFoodItem(foodId, food));
    }
    @DeleteMapping("/delete-food/{foodId}")
    public ResponseEntity<Response> deleteFoodItem(@Valid @PathVariable(value = "foodId")@Min(1) Long foodId){
        return ResponseEntity.ok(foodService.deleteFoodItem(foodId));
    }



}
