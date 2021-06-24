package org.simbirsoft.kokutov.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterForm {
    private String username;
    private String password;
}
