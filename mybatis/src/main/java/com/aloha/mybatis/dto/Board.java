package com.aloha.mybatis.dto;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
// 자동으로 필요한 메서드 만들어주는 편의 기능
// : getter(꺼내기) setter(값 넣기) toString(문자열 출력) equals() / hashCode() / 기본 생성자 등
public class Board {
    private Integer no;
    private String id;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    // 멤버 변수(필드)

    public Board() {
        this.id = UUID.randomUUID().toString();
    }
    // 생성자 Board
    // : Board 객체 생성될 때 자동으로 실행되는 코드
    // -> id 에 랜덤한 고유값(UUID) 넣어줌 (중복 방지)
}


