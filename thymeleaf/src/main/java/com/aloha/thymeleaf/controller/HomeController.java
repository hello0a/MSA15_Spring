package com.aloha.thymeleaf.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aloha.thymeleaf.dto.Person;
import com.aloha.thymeleaf.dto.UserAuth;
import com.aloha.thymeleaf.dto.Users;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {
    
    @GetMapping({"/", "/{page}"})
    public String getMethodName(
        @PathVariable(value = "page", required = false) String page,
        Model model,
        Person person,
        Users loginUser,
        HttpSession session
    ) {
        // Person
        person.setName("ALOHA");
        person.setAge(20);
        model.addAttribute("person", person);

        // 컬렉션
        List<String> items = List.of("item1", "item2", "item3", "item4", "item5");
        model.addAttribute("items", items);

        // User - UserAuth 리스트
        List<Users> userList = List.of(
            Users.builder().username("user1").name("사용자1").build(),
            Users.builder().username("user2").name("사용자2").build(),
            Users.builder().username("user3").name("사용자3").build(),
            Users.builder().username("user4").name("사용자4").build(),
            Users.builder().username("user5").name("사용자5").build()
        );
        UserAuth roleUser = UserAuth.builder().auth("ROLE_USER").build();
        UserAuth roleAdmin = UserAuth.builder().auth("ROLE_ADMIN").build();
        List<UserAuth> authList = List.of( roleUser, roleAdmin );
        for (Users user : userList) {
            user.setAuthList(authList);
        }
        model.addAttribute("userList", userList);

         // 로그인 유저
        loginUser.setNo(1L);
        loginUser.setId(UUID.randomUUID().toString());
        loginUser.setUsername("ALOHA");
        loginUser.setPassword("123456");
        loginUser.setName("김조은");
        loginUser.setGender("여자");
        loginUser.setType("관리자");
        loginUser.setCreatedAt(new Date());
        loginUser.setUpdatedAt(new Date());
        loginUser.setAuthList(authList);
        
        session.setAttribute("loginUser", loginUser);
        model.addAttribute("loginUser", loginUser);
        
        return page == null ? "index" : page;
    }
    
}
