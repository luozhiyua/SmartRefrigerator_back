package mise.userservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;
    private boolean error;
    public UserNotFoundException(String message, boolean error) {
        this.message = message;
        this.error = error;
    }
}