package org.simbirsoft.kokutov.mapper;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.user.UserDto;
import org.simbirsoft.kokutov.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserMapper {

    public UserDto toViewDto(User user) {
        if ( user == null ) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setRole(user.getRole().toString());

        return userDto;
    }

}
