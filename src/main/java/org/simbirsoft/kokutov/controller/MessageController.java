package org.simbirsoft.kokutov.controller;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.MessageDto;
import org.simbirsoft.kokutov.dto.ResponseMessageDto;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseMessageDto> sendMessage(@RequestBody MessageDto messageDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        messageService.save(messageDto, user);
        return ResponseEntity.ok(new ResponseMessageDto("Сообщение отправлено!"));
    }
}
