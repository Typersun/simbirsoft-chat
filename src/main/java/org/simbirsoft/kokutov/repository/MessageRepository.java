package org.simbirsoft.kokutov.repository;

import org.simbirsoft.kokutov.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
