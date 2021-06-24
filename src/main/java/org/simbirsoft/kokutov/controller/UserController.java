package org.simbirsoft.kokutov.controller;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.user.TokenDto;
import org.simbirsoft.kokutov.dto.user.UpdateForm;
import org.simbirsoft.kokutov.mapper.UserMapper;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity getUser(ServletRequest request) {
        return ResponseEntity.ok(userMapper.toViewDto((User) request.getAttribute("user")));
    }

    @DeleteMapping
    public void deleteUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        userService.delete(user);
    }

    @PutMapping
    public TokenDto updateUser(HttpServletRequest request, @RequestBody UpdateForm updateForm) {
        User user = (User) request.getAttribute("user");
        return userService.update(updateForm, user);
    }

    



}
