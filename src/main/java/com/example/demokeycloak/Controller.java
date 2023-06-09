package com.example.demokeycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/customer")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/test")
    public User test() {
        return userRepository.findByUserName("tranvietkhiem1");
    }
}
