package com.aloha.security.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.aloha.security.domain.CustomUser;
import com.aloha.security.domain.Users;
import com.aloha.security.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    
    // @Autowired
    // private UserService userService;
    private final UserService userService;

    /**
     * 메인 화면
     * [GET] - /
     * index.html
     * @return
     * @throws Exception 
     */
    @GetMapping("")
    // User : security User 로 임포트! (dto 아님!)
    // @AuthenticationPrincipal User authUser : 현재 인증된 정보 의존성 주입 
    // -> 이미 인증된 정보들로 사용
    // -> User 가 아닌 UserDetails 을 사용해도 구현 가능!
    // 다이어그램 참고!
    // 방법1
    // public String home(@AuthenticationPrincipal User authUser, Model model) throws Exception {
    // 방법2
    // Authentication : spring security 임포트!
    // -> 현재 인증이 되어 있나 여부 판단 가능
    // public String home(Authentication authentication, Model model) throws Exception {
    // 방법3
    // public String home(@AuthenticationPrincipal UserDetails authUser, Model model) throws Exception {
    public String home(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        log.info("::::::::::메인 화면:::::::::");

        // @AuthenticationPrincipal User authUser, Model model
        // if ( authUser != null) {             //-> 인증된 사용자 객체
        //     log.info("authUser : " + authUser);
        //     String username = authUser.getUsername();
        //     Users user = userService.select(username);
        //     model.addAttribute("user", user);
        // }

        // Authentication authentication, Model model
        // if ( authentication != null ) {
        //     User authUser = (User) authentication.getPrincipal();   // 인증된 사용자 객체
        //     String username = authUser.getUsername();               // 인증된 사용자 아이디
        //     String password = authUser.getPassword();               // 인증된 사용자 비밀번호
        //     Collection<GrantedAuthority> authList = authUser.getAuthorities();  // 사용자 권한
        //     Users user = userService.select(username);
        //     model.addAttribute("user", user);
        // }

        // @AuthenticationPrincipal CustomUser customUser :: 사용자 정의 인증 객체
         if ( customUser != null) {             //-> 인증된 사용자 객체
            log.info("customUser : " + customUser);
            Users user = customUser.getUser();
            model.addAttribute("user", user);
        }
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
    public String joinPro(Users user, HttpServletRequest request) throws Exception{
        log.info(":::::::::회원 가입 처리:::::::::");
        log.info("user : " + user);

        // 암호화 전 비밀번호 ( 미리 저장해두지 않으면, )
        String plainPassword = user.getPassword();
        // 회원 가입 처리
        int result = userService.join(user);
        // 회원 가입 성공 시, 바로 로그인
        if (result > 0) {
            // 암호화 전 비밀번호 다시 세팅 ( 토큰 비밀번호로 회원가입 되어서 로그인 시 오류 발생!)
            user.setPassword(plainPassword); 
            boolean loginResult = userService.login(user, request);
            if (loginResult) {
                return "redirect:/";
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/join?error";
    }
    // 과정!
    // 회원가입 -> 성공 -> 바로 로그인 -> 메인화면

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
    
    /**
     * 로그인 화면
     * [GET] - /login
     * @return
     */
    @GetMapping("/login")
    public String login(
        @CookieValue(value = "remember-id", required = false) Cookie cookie,
        Model model
    ) {
        log.info(":::::::::::::로그인 화면::::::::::::::");
        String username = "";
        boolean rememberId = false;
        if ( cookie != null ) {
            log.info("CookieName : " + cookie.getName());
            log.info("CookieValue : " + cookie.getValue());
            username = cookie.getValue();
            rememberId = true;
        }
        model.addAttribute("username", username);
        model.addAttribute("rememberId", rememberId);
        return "login";
    }
    
}
