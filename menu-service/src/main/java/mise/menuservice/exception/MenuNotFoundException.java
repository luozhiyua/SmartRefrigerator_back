package mise.menuservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//import java.io.Serial;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MenuNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;
    private boolean error;
    public MenuNotFoundException(String message, boolean error) {
        this.message = message;
        this.error = error;
    }
}
