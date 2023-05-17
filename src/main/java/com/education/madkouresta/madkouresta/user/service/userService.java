package com.education.madkouresta.madkouresta.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.madkouresta.madkouresta.user.model.User;
import com.education.madkouresta.madkouresta.user.repo.UserRepo;

@Service
public class userService {
    @Autowired
    private UserRepo repo;

    public List<User> getAllUsersName() {
        return repo.findAll();
    }

    public User getUserById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public User savUser(User user) {
        return repo.save(user);
    }

    public boolean deletUser(User user) {
        try {
            repo.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User updateUser(User user) {
        return repo.save(user);
    }

}
