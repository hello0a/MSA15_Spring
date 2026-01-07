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
    // MultipartFile 란?
    // : Spring 에서 제공하는 업로드된 파일을 다루기 위한 타입
    // 왜 List 가 더 좋은가?
    // : 유연 / 동적으로 파일 개수 조절 가능 / add 및 remove 쉬움 / 대부분 Spring에서는 List 선호

    public Board(Long no, String title, String writer, String content) {
    this.no = no;
    this.title = title;
    this.writer = writer;
    this.content = content;
  }
}
/**
 * 전체 정리
 * 1. @Data : getter/setter 등 자동 생성
 * 2. @NoArgsConstructor : 기본 생성자 생성
 * 3. List<MultipartFile> : 업로드된 파일 목록
 */

/**
 * 비유 설명
 * 1. Board 클래스 : 게시판 글 정보를 담는 상자
 * 2. Field : 상자 안에 들어있는 물건들
 * 3. Lombok : 상자 자동으로 만들고, 열쇠(get/set) 자동으로 만드는 자동 제작 기계
 */
