package org.simbirsoft.kokutov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateForm {
    private String username;
    private String password;
}
