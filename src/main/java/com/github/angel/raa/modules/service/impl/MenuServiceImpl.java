package com.github.angel.raa.modules.service.impl;

import com.github.angel.raa.modules.dto.FoodDTO;
import com.github.angel.raa.modules.dto.MenuDTO;
import com.github.angel.raa.modules.exception.MenuNotFoundException;
import com.github.angel.raa.modules.models.Ingredient;
import com.github.angel.raa.modules.models.Menu;
import com.github.angel.raa.modules.repository.MenuRepository;
import com.github.angel.raa.modules.service.interfaces.MenuService;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map((dto) -> new MenuDTO(dto.getId(), dto.getImage(), dto.getName(), dto.getIngredients(), dto.getSteps()))
                .toList();
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
        menu.setIngredients(body.getIngredients());
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
        dto.setIngredients(menu.getIngredients());
        dto.setSteps(menu.getSteps());
        return dto;
    }

    private Menu convertToEntity(@NonNull MenuDTO dto) {
        Menu menu = new Menu();
        menu.setId(dto.getMenuId());
        menu.setImage(dto.getImage());
        menu.setName(dto.getName());
        menu.setIngredients(dto.getIngredients());
        menu.setSteps(dto.getSteps());
        List<Ingredient> ingredients = dto.getIngredients();
        for (Ingredient i: ingredients){
            i.setMenu(menu);
        }
        return menu;
    }
}
