package com.github.angel.raa.modules.service.impl;

import com.github.angel.raa.modules.dto.FoodDTO;
import com.github.angel.raa.modules.exception.FoodNotFoundException;
import com.github.angel.raa.modules.models.Category;
import com.github.angel.raa.modules.models.Food;
import com.github.angel.raa.modules.repository.FoodRepository;
import com.github.angel.raa.modules.service.interfaces.FoodService;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FoodDTO> getFoodsByDate() {
        List<FoodDTO> foodList = foodRepository.findAll()
                .stream()
                .map((dto) -> new FoodDTO(dto.getId(), dto.getName(), dto.getFreshDate(), dto.getQuantity(), dto.getCategory(), dto.getAddress()))
                .toList();
        ArrayList<FoodDTO> convert = new ArrayList<>(foodList);
        Collections.sort(convert);
        return convert.stream().toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodDTO> getFoodsByName() {
        List<FoodDTO> foodList = foodRepository.findAll()
                .stream()
                .map((dto) -> new FoodDTO(dto.getId(), dto.getName(), dto.getFreshDate(), dto.getQuantity(), dto.getCategory(), dto.getAddress()))
                .toList();
        ArrayList<FoodDTO> convert = new ArrayList<>(foodList);
        convert.sort(new Comparator<FoodDTO>() {
            @Override
            public int compare(FoodDTO food1, FoodDTO food2) {
                String name1 = food1.getName().toLowerCase();
                String name2 = food2.getName().toLowerCase();
                return name1.compareTo(name2);
            }
        });
        return convert.stream().toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodDTO> getAllFoods() {
        return foodRepository.findAll()
                .stream()
                .map((dto) -> new FoodDTO(dto.getId(), dto.getName(), dto.getFreshDate(), dto.getQuantity(), dto.getCategory(), dto.getAddress()))
                .toList();
    }

    @Override
    public List<FoodDTO> getFoodByInput(String input) {
        return foodRepository.findAll()
                .stream()
                .map((dto) -> new FoodDTO(dto.getId(), dto.getName(), dto.getFreshDate(), dto.getQuantity(), dto.getCategory(), dto.getAddress()))
                .filter(food -> food.getName().contains(input))
                .toList();
    }

    @Override
    public List<FoodDTO> getFoodsByCategory(Category category) {
        return foodRepository.findAll()
                .stream()
                .map((dto) -> new FoodDTO(dto.getId(), dto.getName(), dto.getFreshDate(), dto.getQuantity(), dto.getCategory(), dto.getAddress()))
                .filter(food -> food.getCategory().equals(category))
                .toList();
    }

    @Override
    public FoodDTO getFoodItemsById(@NonNull Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found ", true));
        return convertToDto(food);
    }

    @Override
    public Response editFoodItem(@NonNull Long id, FoodDTO body) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found ", true));
        food.setName(body.getName());
        food.setFreshDate(body.getFreshDate());
        food.setCategory(body.getCategory());
        food.setQuantity(body.getQuantity());
        food.setAddress(body.getAddress());
        foodRepository.save(food);
        return Response.builder().message("Food successfully edit").code(201).error(false).build();
    }

    @Override
    public Response addFoodItem(@NonNull FoodDTO body) {
        Food food = convertToEntity(body);
        foodRepository.save(food);
        return Response.builder().message("Food successfully add").code(200).error(false).build();
    }

    @Override
    public Response deleteFoodItem(@NonNull Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found ", true));
        foodRepository.delete(food);
        return Response.builder().message("Food successfully delete").code(204).error(false).build();
    }

    private FoodDTO convertToDto(@NonNull Food food) {
        FoodDTO dto = new FoodDTO();
        dto.setId(food.getId());
        dto.setName(food.getName());
        dto.setFreshDate(food.getFreshDate());
        dto.setQuantity(food.getQuantity());
        dto.setCategory(food.getCategory());
        dto.setAddress(food.getAddress());
        return dto;
    }

    private Food convertToEntity(@NonNull FoodDTO dto) {
        Food food = new Food();
        food.setId(dto.getId());
        food.setName(dto.getName());
        food.setFreshDate(dto.getFreshDate());
        food.setQuantity(dto.getQuantity());
        food.setCategory(dto.getCategory());
        food.setAddress(dto.getAddress());
        return food;
    }

}
