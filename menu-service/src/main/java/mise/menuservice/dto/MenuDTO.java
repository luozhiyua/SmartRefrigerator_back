package mise.menuservice.dto;

//import com.github.angel.raa.modules.models.Ingredient;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mise.menuservice.models.Ingredient;

//import java.io.Serial;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long menuId;
    @NotBlank(message = "image")
    private String image;
    @NotBlank(message = "name")
    private String name;
//    @NotBlank( message = "ingredients")

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
//    @JsonManagedReference
    private List<Ingredient> ingredients;
//    @NotBlank(message = "steps")
    @NotNull
    private List<String> steps;
}
