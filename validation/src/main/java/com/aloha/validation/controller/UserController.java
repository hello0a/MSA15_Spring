package com.aloha.validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.aloha.validation.dto.Users;
import com.aloha.validation.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    /**
     * 회원가입 화면
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/signup")
    // html : user
    // @ModelAttribute 생략 시 users 라고 등록 됨
    public String signup(
        Model model
    ) {
        model.addAttribute("user", new Users());
        return "signup";
    }
    /**
     * 회원가입 처리
     * @param user
     * @param bindingResult
     * @return
     * @throws Exception 
     * @Validated : 지정한 객체에 대해서 유효성 검사 실행
     * BindingResult : 유효성 검사에서 발생한 에러 결과를 담은 객체
     */
    @PostMapping("/signup")
    public String signup(
        @Validated @ModelAttribute("user") Users user,
        BindingResult bindingResult
    ) throws Exception {
        // 유효성 검증 에러 발생하면 -> 다시 회원가입 화면으로
        if ( bindingResult.hasErrors() ) {
            return "signup";
        }
        // 유효성 검증 성공하면, 회원가입 처리 후 메인화면으로
        boolean result = userService.insert(user);
        log.info("회원가입 성공 여부 : " + result);
        return "redirect:/";
    }
    
}
