package com.github.angel.raa.modules.service.impl;

import com.github.angel.raa.modules.dto.FoodDTO;
//import com.github.angel.raa.modules.dto.IngredientDTO;
import com.github.angel.raa.modules.dto.MenuDTO;
import com.github.angel.raa.modules.exception.MenuNotFoundException;
import com.github.angel.raa.modules.models.Ingredient;
import com.github.angel.raa.modules.models.Menu;
import com.github.angel.raa.modules.repository.IngredientRepository;
import com.github.angel.raa.modules.repository.MenuRepository;
import com.github.angel.raa.modules.service.interfaces.FoodService;
import com.github.angel.raa.modules.service.interfaces.IngredientService;
import com.github.angel.raa.modules.service.interfaces.MenuService;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final IngredientService ingredientService;
    private final FoodService foodService;

    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map((dto) -> new MenuDTO(dto.getId(), dto.getImage(), dto.getName(),
                        dto.getIngredients(),
                        dto.getSteps()))
                .toList();
    }

    @Override
    public List<MenuDTO> getAvailableMenus() {
        List<String> foods = foodService.getAllFoods()
                .stream()
                .map(FoodDTO :: getName)
                .toList();
        List<MenuDTO> res = new ArrayList<>();
        for (MenuDTO menu : getAllMenus()){
            List<String> needs = menu.getIngredients()
                    .stream()
                    .filter(Ingredient::isMainIngredient)
                    .map(Ingredient :: getName)
                    .toList();
            if (foods.containsAll(needs)){
                res.add(menu);
            }
        }
        return res;
    }

    @Override
    public List<MenuDTO> getMenusByInput(@NonNull String input) {
//        List<MenuDTO> res = new ArrayList<>();
        List<MenuDTO> res1 = getAllMenus()
                .stream()
                .filter(t -> t.getName().contains(input))
                .toList();
        List<MenuDTO> res2 = getAllMenus()
                .stream()
                .filter(t -> t.getIngredients()
                        .stream()
                        .filter(Ingredient::isMainIngredient)
                        .map(Ingredient::getName)
                        .toList().contains(input))
                .toList();

        return Stream.of(res1, res2)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MenuDTO getMenuById(@NonNull Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Menu not found ", true));
        return convertToDto(menu);
    }

    @Override
    @Transactional
    public Response saveMenu(@NonNull MenuDTO body) {
        Menu menu = convertToEntity(body);
        menuRepository.save(menu);
        return Response.builder().message("Menu saved successfully").code(201).error(false).build();
    }

    @Override
    @Transactional
    public Response updateMenu(@NonNull Long id, MenuDTO body) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Menu not found ", true));
        menu.setImage(body.getImage());
        menu.setName(body.getName());
        menu.setIngredients(
                body.getIngredients()
        );
        menu.setSteps(body.getSteps());
        for (Ingredient i : menu.getIngredients()){
            i.setMenu(menu);
        }
        menuRepository.save(menu);
        return Response.builder().message("Menu successfully updated").code(200).error(false).build();
    }

    @Override
    @Transactional
    public Response deleteMenu(@NonNull Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Employee not found ", true));
        menuRepository.delete(menu);
        return Response.builder().message("Menu successfully deleted").code(200).error(false).build();
    }

    private MenuDTO convertToDto(@NonNull Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setMenuId(menu.getId());
        dto.setImage(menu.getImage());
        dto.setName(menu.getName());
        dto.setIngredients(
                menu.getIngredients()
        );
        dto.setSteps(menu.getSteps());
        return dto;
    }

    private Menu convertToEntity(@NonNull MenuDTO dto) {
        Menu menu = new Menu();
        menu.setId(dto.getMenuId());
        menu.setImage(dto.getImage());
        menu.setName(dto.getName());
        menu.setIngredients(
                dto.getIngredients()

        );
        menu.setSteps(dto.getSteps());
        List<Ingredient> ingredients = dto.getIngredients();

        List<Ingredient> all = ingredientService.getAllIngredient();
        Long lastIndex = 0L;
        if (all.size() > 0){
            lastIndex = all.get(all.size() - 1).getId();
        }
        lastIndex++;

        for (Ingredient i: ingredients){
            i.setMenu(menu);
            i.setId(lastIndex);
            lastIndex++;
        }
        return menu;
    }
}
