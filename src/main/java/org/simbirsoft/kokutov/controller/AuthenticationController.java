package org.simbirsoft.kokutov.controller;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.user.LoginForm;
import org.simbirsoft.kokutov.dto.user.RegisterForm;
import org.simbirsoft.kokutov.dto.user.TokenDto;
import org.simbirsoft.kokutov.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TokenDto registerUser(@RequestBody RegisterForm registerForm) {
        return userService.register(registerForm);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public TokenDto auth(@RequestBody LoginForm loginForm) {
        return userService.login(loginForm);
    }
}
