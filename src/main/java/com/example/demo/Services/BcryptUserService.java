package com.example.demo.Services;


import com.example.demo.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptUserService implements UserService{
    UserRepository userRepo;
    PasswordEncoder passwordEncoder;

    BcryptUserService(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Integer createUser(String username, String email, String password) {
        return userRepo.createUser(username,"CLIENT", email, passwordEncoder.encode(password));
    }
}
