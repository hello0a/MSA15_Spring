package com.aloha.board.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        log.info("메인 화면");
        return "index";
    }
    
}
