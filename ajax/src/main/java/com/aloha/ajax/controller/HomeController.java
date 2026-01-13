package com.aloha.ajax.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "index";
    }
    
    // XMLHttpRequest
    @GetMapping("/ajax/xhr")
    public String xhr() {
        log.info("XMLHttpRequest...");
        return "ajax/xhr";
    }
    
    // fetch
    @GetMapping("/ajax/fetch")
    public String fetch() {
        log.info("fetch...");
        return "ajax/fetch";
    }

    // jquery
    @GetMapping("/ajax/jquery")
    public String jquery() {
        log.info("jQuery $.ajax()...");
        return "ajax/jquery";
    }
    
    
}
