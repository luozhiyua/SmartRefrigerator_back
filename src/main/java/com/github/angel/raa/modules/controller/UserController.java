package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.dto.UserDTO;
import com.github.angel.raa.modules.service.impl.UserServiceImpl;
import com.github.angel.raa.modules.utils.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<Long> userLogin(@Valid @RequestBody UserDTO user){
        Long userId = userService.login(user);
        if(userId == -1) {
            //用户密码不正确
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body((long) -1);
        } else if(userId == -2) {
            //用户未找到
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((long) -2);
        }
        //用户存在且密码正确
        return ResponseEntity.ok(userId);
    }

    @PostMapping("/registry")
    public ResponseEntity<Response> userRegistry(@Valid @RequestBody UserDTO user){
        Response response = userService.addUser(user);
        if (response.getCode() != 201)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Response> deleteUser(@Valid @PathVariable(value = "userId")@Min(1) Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
