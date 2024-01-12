package mise.userservice.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ingredient implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 2L;
    @Id
//    @GeneratedValue
    Long id;
    @Column(name = "ingredientName")
    String name;
    @Column(name = "ingredientQuantity")
    String quantity;
    @Column(name = "mainIngredient")
    boolean mainIngredient;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "menu_id", nullable = false)
    Menu menu;
}
