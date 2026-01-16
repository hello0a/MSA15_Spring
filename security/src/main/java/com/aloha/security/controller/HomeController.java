package com.aloha.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.aloha.security.domain.Users;
import com.aloha.security.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
public class HomeController {
    
    @Autowired
    private UserService userService;

    /**
     * 메인 화면
     * [GET] - /
     * index.html
     * @return
     */
    @GetMapping("")
    public String home() {
        log.info("::::::::::메인 화면:::::::::");
        return "index";
    }

    /**
     * 회원 가입 화면
     * [GET] - /join
     * join.html
     * @return
     */
    @GetMapping("/join")
    public String join() {
        log.info(":::::::::회원 가입 화면:::::::::");
        return "join";
    }

    /**
     * 회원 가입 처리
     * [POST] - /login
     * -> 된다면 /login
     *      안된다면 /join?error
     * @param users
     * @return
     * @throws Exception
     */
    @PostMapping("/join")
    public String joinPro(Users users) throws Exception{
        log.info(":::::::::회원 가입 처리:::::::::");
        log.info("user : " + users);

        int result = userService.join(users);

        if (result > 0) {
            return "redirect:/login";
        }
        return "redirect:/join?error";
    }

    @ResponseBody
    @GetMapping("/check/{username}")
    public ResponseEntity<Boolean> userCheck(@PathVariable("username") String username) throws Exception{
        log.info("아이디 중복 확인 : " + username);
        Users user = userService.select(username);
        // 아이디 중복
        if ( user != null) {
            log.info("중복된 아이디 입니다 - " + username);
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        // 사용 가능한 아이디입니다. 
        log.info("사용 가능한 아이디입니다" + username);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
    
    
    
}
