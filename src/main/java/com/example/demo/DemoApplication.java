package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        BCryptPasswordEncoder be = new BCryptPasswordEncoder();
        //System.out.println(be.encode("aquajim07"));
        //System.out.println(be.matches("aquajim07", "$2a$10$eYICKcWmv4I5qGwDgQkpSeMkBBdthjLrsVSNuD7uUPvYX3ykhEoCi"));

        SpringApplication.run(DemoApplication.class, args);
    }


}
