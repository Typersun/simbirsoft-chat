package org.simbirsoft.kokutov.controller;

import org.simbirsoft.kokutov.dto.HelloDto;
import org.simbirsoft.kokutov.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DefaultController {
    @RequestMapping(value = "/asdasd")
    HelloDto sdfsdfsdf(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new HelloDto(user.getUsername(), "sdasd");
    }
}
