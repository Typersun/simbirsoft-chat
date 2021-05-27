package org.simbirsoft.kokutov.dto;

import lombok.*;
import org.simbirsoft.kokutov.models.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private Set<Role> roles;
}
