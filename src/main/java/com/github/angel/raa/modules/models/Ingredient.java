package com.github.angel.raa.modules.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ingredient implements Serializable {
    @Id
    Long id;
    @Column(name = "ingredientName")
    String name;
    @Column(name = "ingredientQuantity")
    String quantity;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "menu_id", nullable = false)
    Menu menu;
}