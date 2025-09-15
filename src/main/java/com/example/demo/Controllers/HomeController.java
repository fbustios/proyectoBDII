package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public final class HomeController {


    @GetMapping("/home")
    public final String home() {
        return "ClientHomePage";
    }
    @GetMapping("/home/catalog")
    public final String catalog() {
        return "CatalogPage";
    }
    @GetMapping("/login")
    public final String login() {
        return "login";
    }
}
