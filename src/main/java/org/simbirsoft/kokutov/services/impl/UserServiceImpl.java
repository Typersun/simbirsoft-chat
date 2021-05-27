package org.simbirsoft.kokutov.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simbirsoft.kokutov.config.filter.JwtHelper;
import org.simbirsoft.kokutov.dto.LoginForm;
import org.simbirsoft.kokutov.dto.RegisterForm;
import org.simbirsoft.kokutov.dto.TokenDto;
import org.simbirsoft.kokutov.exceptions.InvalidTokenException;
import org.simbirsoft.kokutov.exceptions.NotFoundException;
import org.simbirsoft.kokutov.mapper.UserMapper;
import org.simbirsoft.kokutov.models.Role;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.repository.UserRepository;
import org.simbirsoft.kokutov.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final JwtHelper jwtHelper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getByAuthToken(String token) {
        if (token == null || jwtHelper.validateToken(token) == false) {
            throw new InvalidTokenException("Invalid token or token header not found");
        }
        String username = jwtHelper.getUsernameFromToken(token);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String tokenPassword = jwtHelper.getPasswordFromToken(token);
            if(tokenPassword.equals(user.getPassword()) == false) {
                throw new InvalidTokenException("Wrong password");
            }
            return optionalUser.get();
        } else {
            throw new NotFoundException("User with username " + username + " not found");
        }
    }

    @Override
    public TokenDto login(LoginForm form) {
        User user = userRepository.findByUsername(form.getUsername())
                .orElseThrow(() -> new NotFoundException("User with name " + form.getUsername() + " not found"));
        return new TokenDto(jwtHelper.generateToken(user));
    }

    @Override
    public TokenDto register(RegisterForm form) {
        User user = User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .roles(Collections.singleton(Role.ROLE_USER))
                .build();
        User savedUser = userRepository.save(user);
        return new TokenDto(jwtHelper.generateToken(savedUser));
    }


}
