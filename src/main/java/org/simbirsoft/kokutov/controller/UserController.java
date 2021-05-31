package org.simbirsoft.kokutov.controller;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.mapper.UserMapper;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    ResponseEntity getUser(ServletRequest request) {
        return ResponseEntity.ok(userMapper.toViewDto((User) request.getAttribute("user")));
    }

    



}
