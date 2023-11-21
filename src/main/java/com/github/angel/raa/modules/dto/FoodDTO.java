package com.github.angel.raa.modules.dto;

import com.github.angel.raa.modules.models.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO implements Serializable, Comparable<FoodDTO> {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "name")
    private String name;
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
