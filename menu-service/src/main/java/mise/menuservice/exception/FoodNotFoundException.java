package mise.menuservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FoodNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;
    private boolean error;
    public FoodNotFoundException(String message, boolean error) {
        this.message = message;
        this.error = error;
    }
}
