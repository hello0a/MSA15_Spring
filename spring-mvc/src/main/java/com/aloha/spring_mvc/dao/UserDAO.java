package com.aloha.spring_mvc.dao;

import org.springframework.stereotype.Repository;

import com.aloha.spring_mvc.dto.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserDAO {
    // 회원가입
    public int signup(User user) {
      log.info("회원가입 데이터");  
      int result = 0;
      // TODO : 데이터 등록 요청
      return result;
    }
}
