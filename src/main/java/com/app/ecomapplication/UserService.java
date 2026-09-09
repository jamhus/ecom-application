package com.app.ecomapplication;

import Entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers() {
        return userList;
    }

    public void createUser(User user) {
        long id = userList.size() + 1L;
        user.setId(id);
        userList.add(user);
    }

    public Optional<User> findUserById(Long id) {

        return userList.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst();
    }

    public boolean updateUser(Long id, User model) {
        return userList.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .map(user -> {
                user.setFirstName(model.getFirstName());
                user.setLastName(model.getLastName());
                return true;
            })
            .orElse(false);
    }
}
