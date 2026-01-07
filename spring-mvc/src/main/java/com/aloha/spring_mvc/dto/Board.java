package com.aloha.spring_mvc.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
// @RequiredArgsConstructor
// final 필수 생성자 생성
public class Board {
    private Long no;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    // 파일 데이터
    // private MultipartFile[] fileList; -> 배열
    private List<MultipartFile> fileList;

    public Board(Long no, String title, String writer, String content) {
    this.no = no;
    this.title = title;
    this.writer = writer;
    this.content = content;
  }
}
