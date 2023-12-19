package com.github.angel.raa.modules.service.impl;

import com.github.angel.raa.modules.models.Ingredient;
import com.github.angel.raa.modules.repository.IngredientRepository;
import com.github.angel.raa.modules.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Ingredient> getAllIngredient() {
        return ingredientRepository.findAll();
    }
}
