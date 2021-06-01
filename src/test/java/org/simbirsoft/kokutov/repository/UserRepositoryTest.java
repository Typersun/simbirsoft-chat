package org.simbirsoft.kokutov.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.simbirsoft.kokutov.models.Role;
import org.simbirsoft.kokutov.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringJUnitConfig
class UserRepositoryTest {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        //given
        User user = User.builder()
                .username("Nebuchadnezzar the Great")
                .password("Babylon")
                .role(Role.ROLE_ADMIN)
                .build();

        userRepository.save(user);
        //when
        Optional<User> userUnderTest = userRepository.findByUsername("Nebuchadnezzar the Great");
        //then
        assertEquals(user.getUsername(), userUnderTest.get().getUsername());
    }
}