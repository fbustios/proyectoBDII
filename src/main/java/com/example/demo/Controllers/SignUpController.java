package com.example.demo.Controllers;

import com.example.demo.DTOS.SignUpDTO;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public final class SignUpController {

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("signUpDTO", new SignUpDTO());
        return "SignUp";
    }


    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //igual mover al user service
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute SignUpDTO signUpDTO, BindingResult bindingResult) {

        if (!signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Las contraseñas no coinciden");
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/signup";
        }
        User usuario = new User(signUpDTO.getUsername(),"CLIENT",signUpDTO.getEmail(), passwordEncoder().encode(signUpDTO.getPassword()));  //mover a un user service

        return "redirect:/home";
    }
}
