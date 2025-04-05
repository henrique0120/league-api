package com.henrique.League_API.service.impl;

<<<<<<< HEAD
import com.henrique.League_API.entities.Accounts;
=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
import com.henrique.League_API.entities.User;
import com.henrique.League_API.repository.UserRepository;
import com.henrique.League_API.service.UserService;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.NoSuchElementException;

=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478

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
<<<<<<< HEAD

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }



=======
>>>>>>> 0847c75125be96d33fae4c4fd08dc366974d5478
}
