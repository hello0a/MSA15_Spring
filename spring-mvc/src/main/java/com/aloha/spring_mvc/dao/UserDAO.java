package com.aloha.spring_mvc.dao;

import org.springframework.stereotype.Repository;

import com.aloha.spring_mvc.dto.User;

import lombok.extern.slf4j.Slf4j;

@Repository
// DAO 역할 표시
// : DB 관련 기능을 담당하는 DAO
// -> Spring 이 자동으로 객체(bean) 만들어서 Service 에서 @Autowired 로 가져와 쓸 수 있음
// 즉, DB 담당자
@slf4j
// 로그 출력 도구 자동 생성
// log 라는 이름의 Logger 객체 자동으로 만들어줌
public class UserDAO {
    // 회원가입
    public int signup(User user) {
    // 파라미터 : User 객체
    // -> 회원가입 시 입력한 정보 담긴 객체
      log.info("회원가입 데이터");  
      int result = 0;
      // TODO : 데이터 등록 요청
      return result;
    }
}
