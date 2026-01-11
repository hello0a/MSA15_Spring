package com.aloha.mybatis.service;

import java.util.List;

import com.aloha.mybatis.dto.Board;

/**
 * BoardService 설명
 * : Controller(요청 받는 곳) 와 Mapper (DB 연결하는 곳) 사이의 중간 관리자!
 * -> 비즈니스 로직(검증, 처리, 조건 체크 등) 담당
 * 
 * 왜 인터페이스인가
 * - 구현 클래스를 마음대로 바꿀 수 있어서 구조 깔끔
 * - 테스트 편리
 * - 유지보수 간편
 */
public interface BoardService {
     // 게시글 목록
    List<Board> list() throws Exception;
    // Controller 가 화면에 보여줄 때 쓸 데이터
    // 게시글 조회
    Board select(Integer no) throws Exception;
    // 게시글 등록
    boolean  insert(Board board) throws Exception;
    // 게시글 수정
    boolean  update(Board board) throws Exception;
    // 게시글 삭제
    boolean  delete(Integer no) throws Exception;
}

/**
 * 한 문장 요약
 * : Service는 Controller 와 Mapper 사이에서
 * Mapper 의 결과 (int) 를 사람이 이해하기 쉬운 boolean 등 으로 바꿔주는 뇌 역할
 */
