package mise.menuservice.dto;

//import com.github.angel.raa.modules.models.Category;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mise.menuservice.models.Category;

//import java.io.Serial;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO implements Serializable, Comparable<FoodDTO> {

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
//        Date thisDate = this.freshDate;
//        Date foodDate = other.freshDate;
//        if (thisDate.after(foodDate)){
//            return 1;
//        }
//        return -1;
    }
}
