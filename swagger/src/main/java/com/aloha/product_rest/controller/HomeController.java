package com.aloha.product_rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    
    @GetMapping("")
    public String home() {
        // Swagger 기본 경로 : /swagger-ui/index.html
        return "redirect:/swagger-ui/index.html";
    }
    
}
