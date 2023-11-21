package com.github.angel.raa.modules.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "menus")
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;

//    @Column(name = "ingredients")
    @JsonManagedReference
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @ElementCollection
    @CollectionTable(name = "menu_steps", joinColumns = @JoinColumn(name = "menu_id"))
    @Column(name = "steps")
    private List<String> steps;
}
