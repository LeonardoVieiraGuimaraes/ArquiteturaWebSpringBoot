package com.example.ArquiteturaWebSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping({"/", "/home", "/index"})
    public String home() {
        return "home";
    }
}
