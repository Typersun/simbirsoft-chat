package org.simbirsoft.kokutov.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simbirsoft.kokutov.dto.ConnectToRoomDto;
import org.simbirsoft.kokutov.dto.RoomDto;
import org.simbirsoft.kokutov.dto.RoomNameDto;
import org.simbirsoft.kokutov.dto.RoomRenameDto;
import org.simbirsoft.kokutov.exceptions.AlreadyExistsException;
import org.simbirsoft.kokutov.exceptions.ForbiddenActionException;
import org.simbirsoft.kokutov.exceptions.NotFoundException;
import org.simbirsoft.kokutov.models.Role;
import org.simbirsoft.kokutov.models.Room;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.repository.RoomRepository;
import org.simbirsoft.kokutov.services.RoomService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public void create(RoomNameDto roomNameDto, User user) {
        Optional<Room> roomToCheckIfExists = roomRepository.findByName(roomNameDto.getName());
        if (roomToCheckIfExists.isPresent()) {
            throw new AlreadyExistsException("Комната с именем " + roomNameDto.getName() + " уже существует");
        } else {
        Room roomToSave = Room.builder()
                .name(roomNameDto.getName())
                .owner(user)
                .participants(new HashSet<>(){{add(user);}})
                .build();
        roomRepository.save(roomToSave);
        }
    }

    @Override
    public void delete(RoomNameDto roomNameDto, User user) {
        Room roomToDelete = (roomRepository.findByName(roomNameDto.getName()))
                .orElseThrow(() -> new NotFoundException("Комната с именем " + roomNameDto.getName() + " не найдена"));
        if (user.getRole() == Role.ROLE_ADMIN || user.getId().equals(roomToDelete.getOwner().getId())) {
            roomRepository.delete(roomToDelete);
        } else {
            throw new ForbiddenActionException("Пользователь не имеет прав на удаление этой комнаты");
        }


    }

    @Override
    public void rename(RoomRenameDto roomRenameDto, User user) {
        Room roomToRename = (roomRepository.findByName(roomRenameDto.getOldName()))
                .orElseThrow(() -> new NotFoundException("Комната с именем " + roomRenameDto.getOldName() + " не найдена"));

        Optional<Room> roomToCheckIfExists = roomRepository.findByName(roomRenameDto.getNewName());
        if (roomToCheckIfExists.isPresent()) {
            throw new AlreadyExistsException("Комната с именем " + roomRenameDto.getNewName() + " уже существует");
        }
        if (user.getRole() == Role.ROLE_ADMIN || user.equals(roomToRename.getOwner())) {
            roomToRename.setName(roomRenameDto.getNewName());
            roomRepository.save(roomToRename);
        } else {
            throw new ForbiddenActionException("Пользователь не имеет прав на переименование этой комнаты");
        }


    }

    @Override
    public void addUserToRoom(ConnectToRoomDto connectToRoomDto, User user) {
        Room roomToAddUser = (roomRepository.findByName(connectToRoomDto.getRoomName()))
                .orElseThrow(() -> new NotFoundException("Комната с именем " + connectToRoomDto.getRoomName() + " не найдена"));
        if (roomToAddUser.getParticipants().contains(user)) {
            throw new AlreadyExistsException("Пользователь уже является участником этой комнаты");
        }
        roomToAddUser.getParticipants().add(user);
        roomRepository.save(roomToAddUser);
    }
}
