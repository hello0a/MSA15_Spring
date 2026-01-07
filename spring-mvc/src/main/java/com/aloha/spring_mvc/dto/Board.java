package com.aloha.spring_mvc.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
// final 필수 생성자 생성
public class Board {
    private final Long no;
    private final String title;
    private final String writer;
    private final String content;
    private Date createdAt;
    private Date updatedAt;
}
