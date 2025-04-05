package com.henrique.League_API.controller;

import com.henrique.League_API.entities.User;
import com.henrique.League_API.repository.UserRepository;
import com.henrique.League_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
<<<<<<< HEAD

=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
    @Autowired
    private UserRepository repository;

//    @GetMapping("/{gameName}")
//    public ResponseEntity<UserDTO> findByGameName(@PathVariable String gameName) {
//        User user = userService.findByGameName(gameName);
//        return ResponseEntity.ok(new UserDTO(User));
//    }

<<<<<<< HEAD
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
<<<<<<< HEAD




=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
}

