package com.example.demo.Controllers;

import com.example.demo.DTOS.SignUpDTO;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public final class SignUpController {
    UserService userService;

    SignUpController(final UserService service){
        this.userService = service;
    }


    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("signUpDTO", new SignUpDTO());
        return "SignUp";
    }


    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //igual mover al user service
    }

    @PostMapping("/signup")
    public String registerUser(final @Valid @ModelAttribute SignUpDTO signUpDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Las contraseñas no coinciden");
        }

        int exito = userService.createUser(signUpDTO.getUsername(),signUpDTO.getEmail(),signUpDTO.getPassword());

        if(exito == 1) redirectAttributes.addFlashAttribute("msgSuccess","Usuario creado correctamente");

        if(bindingResult.hasErrors() || exito == 0) redirectAttributes.addFlashAttribute("msgError","No se pudo crear la cita");

        return "redirect:/home";
    }
}
