package com.henrique.League_API.controller;

import com.henrique.League_API.entities.User;
import com.henrique.League_API.repository.UserRepository;
import com.henrique.League_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private UserRepository repository;

//    @GetMapping("/{gameName}")
//    public ResponseEntity<UserDTO> findByGameName(@PathVariable String gameName) {
//        User user = userService.findByGameName(gameName);
//        return ResponseEntity.ok(new UserDTO(User));
//    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}

