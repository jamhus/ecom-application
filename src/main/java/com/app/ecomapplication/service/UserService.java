package com.app.ecomapplication.service;

import com.app.ecomapplication.repository.UserRepository;
import com.app.ecomapplication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {

        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {

        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User model) {
        return userRepository.findById(id)
            .map(user -> {
                user.setFirstName(model.getFirstName());
                user.setLastName(model.getLastName());
                return true;
            })
            .orElse(false);
    }
}
