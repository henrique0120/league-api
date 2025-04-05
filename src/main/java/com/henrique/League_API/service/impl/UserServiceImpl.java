package com.henrique.League_API.service.impl;

import com.henrique.League_API.entities.Accounts;
import com.henrique.League_API.entities.User;
import com.henrique.League_API.repository.UserRepository;
import com.henrique.League_API.service.UserService;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByUsername(userToCreate.getUsername())) {
            throw new IllegalArgumentException("This game name already exists.");
        }
        return userRepository.save(userToCreate);
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }


}
