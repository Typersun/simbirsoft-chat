package org.simbirsoft.kokutov.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.simbirsoft.kokutov.dto.message.MessageDto;
import org.simbirsoft.kokutov.exceptions.NotFoundException;
import org.simbirsoft.kokutov.models.Message;
import org.simbirsoft.kokutov.models.Role;
import org.simbirsoft.kokutov.models.Room;
import org.simbirsoft.kokutov.models.User;
import org.simbirsoft.kokutov.repository.MessageRepository;
import org.simbirsoft.kokutov.repository.RoomRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {

    @InjectMocks
    private MessageServiceImpl messageService;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private RoomRepository roomRepository;

    private MessageDto messageDto;
    private Room room;
    private User user;
    private Message messageToSave;


    @BeforeEach
    void setUp() {
        //given
        messageDto = new MessageDto("message", "testRoom");
        user = User.builder()
                .id(1L)
                .username("Gilgamesh")
                .password("Enkidu")
                .role(Role.ROLE_USER)
                .rooms(new HashSet<>())
                .build();
        room = Room.builder()
                .id(1L)
                .name("testRoom")
                .owner(user)
                .participants(new HashSet<>())
                .build();
        user.getRooms().add(room);
        room.getParticipants().add(user);

        messageToSave = Message.builder()
                .message("message")
                .room(room)
                .author(user)
                .build();

    }
    @Test
    public void canSaveProperMessage() {
        //when
        doReturn(Optional.of(room)).when(roomRepository).findByName(anyString());
        messageService.save(messageDto, user);

        //then
        ArgumentCaptor<Message> argumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(argumentCaptor.capture());
        Message capturedMessage = argumentCaptor.getValue();
        assertThat(messageToSave).isEqualTo(capturedMessage);
    }
    @Test
    public void whenExceptionThrown_thenExpectationSatisfied() {
        //when
        doReturn(Optional.empty()).when(roomRepository).findByName(anyString());
        //then
        assertThrows(NotFoundException.class, () -> {
            messageService.save(messageDto, user);
        });
    }
}
