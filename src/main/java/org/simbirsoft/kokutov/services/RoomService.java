package org.simbirsoft.kokutov.services;

import org.simbirsoft.kokutov.dto.room.ConnectToRoomDto;
import org.simbirsoft.kokutov.dto.room.RoomNameDto;
import org.simbirsoft.kokutov.dto.room.RoomRenameDto;
import org.simbirsoft.kokutov.models.User;

public interface RoomService {
    void create(RoomNameDto roomNameDto, User user);
    void delete(RoomNameDto roomNameDto, User user);
    void rename(RoomRenameDto roomRenameDto, User user);
    void addUserToRoom(ConnectToRoomDto connectToRoomDto, User user);
}
