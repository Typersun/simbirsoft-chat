package org.simbirsoft.kokutov.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomRenameDto {
    private String oldName;
    private String newName;
}
