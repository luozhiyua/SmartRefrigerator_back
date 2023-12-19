package com.github.angel.raa.modules.service.impl;

import com.github.angel.raa.modules.dto.UserDTO;
import com.github.angel.raa.modules.exception.UserNotFoundException;
import com.github.angel.raa.modules.models.User;
import com.github.angel.raa.modules.repository.UserRepository;
import com.github.angel.raa.modules.service.interfaces.UserService;
import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Response addUser(@NonNull UserDTO body) {
        //检查名称是否已经存在
        Optional<User> userOptional = userRepository.findByUsername(body.getUsername());
        if (userOptional.isPresent()) {
            return Response.builder().message("Username already exists").code(409).error(false).build();
        }
        User user = convertToEntity(body);
        userRepository.save(user);
        return Response.builder().message("User successfully add").code(201).error(false).build();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Long login(@NonNull UserDTO body){
        String username = body.getUsername();
        String inputPassword = body.getPassword();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            // 找到了用户
            User user = userOptional.get();
            if (!Objects.equals(user.getPassword(), inputPassword)) {
                //用户密码不正确
                return (long) -1;
            }
            return user.getId();
        } else {
            // 没有找到用户
            return (long) -2;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Response deleteUser(@NonNull Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found ", true));
        userRepository.delete(user);
        return Response.builder().message("User successfully delete").code(204).error(false).build();
    }

    private User convertToEntity(@NonNull UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
