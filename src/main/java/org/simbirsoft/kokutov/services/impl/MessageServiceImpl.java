package org.simbirsoft.kokutov.services.impl;

import lombok.AllArgsConstructor;
import org.simbirsoft.kokutov.dto.message.MessageDto;
import org.simbirsoft.kokutov.dto.message.MessageMetricsDto;
import org.simbirsoft.kokutov.exceptions.NotFoundException;
import org.simbirsoft.kokutov.models.Message;
import org.simbirsoft.kokutov.models.Room;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.repository.MessageRepository;
import org.simbirsoft.kokutov.repository.RoomRepository;
import org.simbirsoft.kokutov.services.MessageService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private KafkaTemplate<String, MessageMetricsDto> kafkaTemplate;

    @Override
    public void save(MessageDto messageDto, User user) {
        Optional<Room> roomToSendMessage = roomRepository.findByName(messageDto.getRoomName());
        if (roomToSendMessage.isEmpty()) {
            throw new NotFoundException("Комната с именем " + messageDto.getRoomName() + " не найдена");
        }
        Message messageToSave = Message.builder()
                .message(messageDto.getMessage())
                .author(user)
                .room(roomToSendMessage.get())
                .build();

        messageRepository.save(messageToSave);
        kafkaTemplate.send("metrics_messages",
                new MessageMetricsDto(messageToSave.getMessage(), user.getUsername()));

    }
}
