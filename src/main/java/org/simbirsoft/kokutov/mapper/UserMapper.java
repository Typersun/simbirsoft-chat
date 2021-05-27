package org.simbirsoft.kokutov.mapper;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.UserDto;
import org.simbirsoft.kokutov.models.Role;
import org.simbirsoft.kokutov.models.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDto.setRoles(new HashSet<>(set) );
        }

        return userDto;
    }

    public List<UserDto> toListDtoConvert(List<User> users) {
        return users
                .stream()
                .map(this::toViewDto)
                .collect(Collectors.toList());
    }
}
