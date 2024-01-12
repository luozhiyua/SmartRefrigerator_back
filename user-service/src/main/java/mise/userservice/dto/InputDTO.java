package mise.userservice.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class InputDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "input")
    private String input;

    public String getInput() {
        return input;
    }
}
