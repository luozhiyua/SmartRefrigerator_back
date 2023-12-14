package com.github.angel.raa.modules.service.impl;

import com.github.angel.raa.modules.dto.FoodDTO;
//import com.github.angel.raa.modules.dto.IngredientDTO;
import com.github.angel.raa.modules.dto.MenuDTO;
import com.github.angel.raa.modules.exception.MenuNotFoundException;
import com.github.angel.raa.modules.models.Ingredient;
import com.github.angel.raa.modules.models.Menu;
import com.github.angel.raa.modules.models.User;
import com.github.angel.raa.modules.repository.IngredientRepository;
import com.github.angel.raa.modules.repository.MenuRepository;
import com.github.angel.raa.modules.repository.UserRepository;
import com.github.angel.raa.modules.service.interfaces.FoodService;
import com.github.angel.raa.modules.service.interfaces.IngredientService;
import com.github.angel.raa.modules.service.interfaces.MenuService;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
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
    public List<MenuDTO> getAvailableMenus(Long userId) {
        List<String> foods = foodService.getAllFoods(userId)
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
    public List<MenuDTO> getMenusByCollect(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getCollectedMenus().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<MenuDTO> getMenusByInput(@NonNull String input) {
//        List<MenuDTO> res = new ArrayList<>();
        List<MenuDTO> res1 = getAllMenus()
                .stream()
                .filter(t -> t.getName().contains(URLDecoder.decode(input, StandardCharsets.UTF_8)))
                .toList();
        List<MenuDTO> res2 = getAllMenus()
                .stream()
                .filter(t -> t.getIngredients()
                        .stream()
                        .filter(Ingredient::isMainIngredient)
                        .map(Ingredient::getName)
                        .toList().contains(URLDecoder.decode(input, StandardCharsets.UTF_8)))
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
    public Response collectMenu(@NonNull Long id, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Employee not found ", true));
        user.getCollectedMenus().add(menu);
        userRepository.save(user);
        return Response.builder().message("Menu successfully collected").code(200).error(false).build();
    }

    @Override
    public Response uncollectMenu(@NonNull Long id, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Menu> collectedMenus = user.getCollectedMenus();
        collectedMenus.removeIf(menu -> menu.getId().equals(id));
        user.setCollectedMenus(collectedMenus); // 重新设置更新后的收藏菜单列表
        userRepository.save(user); // 保存对用户对象的更改
        return Response.builder().message("Menu successfully uncollected").code(200).error(false).build();
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
