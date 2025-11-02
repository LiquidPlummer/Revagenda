package com.revature.revagenda_server.services;

import com.revature.revagenda_server.models.User;
import com.revature.revagenda_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        return this.userRepo.findAll();
    }
}
