package mise.foodservice.dto;

//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mise.foodservice.models.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO implements Serializable, Comparable<FoodDTO>{
//    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "name")
    private String name;
    @NotBlank(message = "userId")
    private String userId;
    @NotNull(message = "freshDate")
    private Date freshDate;
    @NotBlank(message = "quantity")
    private String quantity;
    @NotNull(message = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotBlank(message = "address")
    private String address;

    @Override
    public int compareTo(FoodDTO other) {
        return this.freshDate.compareTo(other.freshDate);
    }

}
