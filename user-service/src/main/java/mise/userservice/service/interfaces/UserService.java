package mise.userservice.service.interfaces;

//import com.github.angel.raa.modules.dto.UserDTO;
//import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import mise.userservice.dto.UserDTO;
import mise.userservice.utils.Response;

public interface UserService {
    Response addUser(@NonNull UserDTO body);
    Long login(@NonNull UserDTO body);
    Response deleteUser(@NonNull Long id);
}
