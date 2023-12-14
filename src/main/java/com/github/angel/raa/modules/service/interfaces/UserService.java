package com.github.angel.raa.modules.service.interfaces;

import com.github.angel.raa.modules.dto.UserDTO;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;

public interface UserService {
    Response addUser(@NonNull UserDTO body);
    Long login(@NonNull UserDTO body);
    Response deleteUser(@NonNull Long id);
}
