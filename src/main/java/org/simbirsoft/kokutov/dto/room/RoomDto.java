package org.simbirsoft.kokutov.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.simbirsoft.kokutov.models.User;

@Getter
@Setter
@AllArgsConstructor
public class RoomDto {
    private String RoomName;
    private User owner;
}
