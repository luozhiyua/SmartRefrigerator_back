package mise.menuservice.service.impl;

//import com.github.angel.raa.modules.models.Ingredient;
//import com.github.angel.raa.modules.repository.IngredientRepository;
//import com.github.angel.raa.modules.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import mise.menuservice.models.Ingredient;
import mise.menuservice.repository.IngredientRepository;
import mise.menuservice.service.interfaces.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getAllIngredient() {
        return ingredientRepository.findAll();
    }
}
