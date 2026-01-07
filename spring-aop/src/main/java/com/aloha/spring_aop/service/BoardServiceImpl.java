package com.aloha.spring_aop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.spring_aop.dao.BoardDAO;
import com.aloha.spring_aop.dto.Board;

import lombok.extern.slf4j.Slf4j;

/**
 * 해당 클래스의 정체 : Service 구현체
 * @Service 
 * : Spring 이 "서비스 계층" 으로 인식하고 스프링 빈으로 등록
 * implements BoardService
 * : 서비스 인터페이스를 실제로 구현한 클래스
 * 
 * 컨트롤러에서는 이 구현체가 자동으로 주입됨
 */
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;
    // DAO(데이터 접근 계층) 의존성 주입
    // : Service 에서는 DAO 호출해서 DB 접근
    // : Spring 한테 BoardDAO 객체 하나 만들어서 여기다가 넣어줘! 라고 부탁하는 코드

    @Override
    public List<Board> list() throws Exception {
        List<Board> list = boardDAO.list();
        int count = list.size();
        log.info("게시글 목록을 조회합니다...");
        log.info("게시글 개수 : {}", count);
        return list;
    }
    // 목록 list
    // 1. DAO에서 게시글 목록 가져오고,
    // 2. 게시글 개수 출력하고,
    // 3. 그대로 Controller로 반환
    // 즉, 게시글 목록 요청이 오면 DAO에게 시키고 그 결과 개수를 로그로 찍고 다시 돌려보낸다!

    @Override
    public Board select(Long no) throws Exception {
        Board board = boardDAO.select(no);
        log.info("게시글을 조회합니다...");
        int test = 10 / 0;
        return board;
    }
    // 조회 select
    // 1. 게시글 번호로 DAO에서 글 하나 가져옴
    // 2. 조회합니다 로그 출력
    // 3. 10 / 0 -> 무조건 오류 발생
    // 주의! 반드시 예외 발생함
    //      아마도 예외 상황 테스트하기 위한 코드일 가능성 큼

    @Override
    public boolean insert(Board board) throws Exception {
        int result = boardDAO.insert(board);
        log.info("게시글 등록...");
        return result > 0;
    }
    // 등록 insert
    // 1. DAO에서 inser 실행
    // 2. result 가 1이면 true, 0이면 false
    // 실제 DB에서 executeUpdate() 성공하면 1반환하니까 일반적인 구조

    @Override
    public boolean update(Board board) throws Exception {
       int result = boardDAO.update(board);
       log.info(("게시글 수정..."));
       return result > 0;
    }
    
    @Override
    public boolean delete(Long no) throws Exception {
        int result = boardDAO.delete(no);
        log.info(("게시글 삭제..."));
        return result > 0;
    }
    // 수정 update, 삭제 delete
    // insert와 같은 패턴
}
/**
 * 초보자를 위한 전체 흐름 요약
 * Controller (요청 받아옴)
       ↓
    Service (비즈니스 로직 담당)
       ↓
    DAO (DB 접근 담당)
       ↓
    DB 또는 임시데이터 제공

 */
