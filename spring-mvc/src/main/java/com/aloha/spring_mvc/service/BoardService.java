package com.aloha.spring_mvc.service;

import java.util.List;

import com.aloha.spring_mvc.dto.Board;

public interface BoardService {
    
    // 게시글 목록
    List<Board> list() throws Exception;
    // 게시글 조회
    Board select(Long no) throws Exception;
    // 게시글 등록
    boolean insert(Board board) throws Exception;
    // 게시글 수정
    boolean update(Board board) throws Exception;
    // 게시글 삭제
    boolean delete(Long no) throws Exception;
}
