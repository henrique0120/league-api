package com.henrique.League_API.service;

import com.henrique.League_API.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User create(User userToCreate);

}