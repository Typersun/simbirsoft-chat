package org.simbirsoft.kokutov.services;


import org.simbirsoft.kokutov.dto.MessageDto;
import org.simbirsoft.kokutov.models.User;

public interface MessageService {
    void save(MessageDto messageDto, User user);
}
