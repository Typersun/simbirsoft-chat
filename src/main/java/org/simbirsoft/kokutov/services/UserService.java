package org.simbirsoft.kokutov.services;

import org.simbirsoft.kokutov.dto.LoginForm;
import org.simbirsoft.kokutov.dto.RegisterForm;
import org.simbirsoft.kokutov.dto.TokenDto;
import org.simbirsoft.kokutov.dto.UpdateForm;
import org.simbirsoft.kokutov.models.User;

public interface UserService {
    User getByAuthToken(String token);
    TokenDto register(RegisterForm form);
    TokenDto login(LoginForm form);
    TokenDto update(UpdateForm updateForm, User user);
    void delete(User user);
}