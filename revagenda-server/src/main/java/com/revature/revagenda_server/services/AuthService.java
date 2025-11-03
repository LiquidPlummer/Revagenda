package com.revature.revagenda_server.services;

import com.revature.revagenda_server.dtos.RegistrationDto;
import com.revature.revagenda_server.models.Auth;
import com.revature.revagenda_server.models.User;
import com.revature.revagenda_server.repositories.AuthRepository;
import com.revature.revagenda_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthRepository authRepo;
    private UserRepository userRepo;

    @Autowired
    public AuthService(AuthRepository authRepo, UserRepository userRepo) {
        this.authRepo = authRepo;
        this.userRepo = userRepo;
    }

    public User register(RegistrationDto registrationDto) {
        Auth auth = new Auth(registrationDto.getUsername(), registrationDto.getPassword());
        authRepo.save(auth);

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setAuth(auth);
        return userRepo.save(user);
    }
}
