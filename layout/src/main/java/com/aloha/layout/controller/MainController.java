package com.aloha.layout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    
    @GetMapping("/main")
    public String main() {
        return "main";
    }
    
}
