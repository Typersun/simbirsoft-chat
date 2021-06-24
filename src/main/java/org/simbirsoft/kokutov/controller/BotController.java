package org.simbirsoft.kokutov.controller;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.room.ConnectToRoomDto;
import org.simbirsoft.kokutov.dto.message.ResponseMessageDto;
import org.simbirsoft.kokutov.dto.room.RoomNameDto;
import org.simbirsoft.kokutov.dto.room.RoomRenameDto;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/bot")
@AllArgsConstructor
public class BotController {

    private final RoomService roomService;


    @RequestMapping(value = "/room/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessageDto> createRoom(@RequestBody RoomNameDto roomNameDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        roomService.create(roomNameDto, user);
        return ResponseEntity.ok(new ResponseMessageDto("Комната успешно создана"));
    }
    @RequestMapping(value = "/room/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessageDto> removeRoom(@RequestBody RoomNameDto roomNameDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        roomService.delete(roomNameDto, user);
        return ResponseEntity.ok(new ResponseMessageDto("Комната успешно удалена"));
    }

    @RequestMapping(value = "/room/rename", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResponseMessageDto> renameRoom(@RequestBody RoomRenameDto roomRenameDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        roomService.rename(roomRenameDto, user);
        return ResponseEntity.ok(new ResponseMessageDto("Комната успешно переименована"));
    }
    @RequestMapping(value = "/room/connect", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ResponseMessageDto> connectToRoom(@RequestBody ConnectToRoomDto connectToRoomDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        roomService.addUserToRoom(connectToRoomDto, user);
        return ResponseEntity.ok(new ResponseMessageDto("Пользователь успешно добавлен в комнату"));
    }

}
