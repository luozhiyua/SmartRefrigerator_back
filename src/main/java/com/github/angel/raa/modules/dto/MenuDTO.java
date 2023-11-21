package com.github.angel.raa.modules.dto;

import com.github.angel.raa.modules.models.Ingredient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long menuId;
    @NotBlank(message = "image")
    private String image;
    @NotBlank(message = "name")
    private String name;
//    @NotBlank( message = "ingredients")
    @NotNull
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;
//    @NotBlank(message = "steps")
    @NotNull
    private List<String> steps;
}
