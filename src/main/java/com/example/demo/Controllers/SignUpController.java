package com.example.demo.Controllers;

import com.example.demo.DTOS.SignUpDTO;
import com.example.demo.Repositories.UserRepository;
import jakarta.validation.Valid;
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

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute SignUpDTO signUpDTO, BindingResult bindingResult) {

        if (!signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Las contraseñas no coinciden");
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/signup";
        }
        return "redirect:/home";
    }
}
