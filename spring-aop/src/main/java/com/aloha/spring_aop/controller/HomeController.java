package com.aloha.spring_aop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.aloha.spring_aop.dto.Board;
import com.aloha.spring_aop.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 전체 구조 한눈에 보기!
 * - 웹 브라우저에서 "/" 주소로 접근했을 때
 *  1. 게시글 목록 가져오고,
 *  2. 게시글 하나 조회하고,
 *  3. index.html 화면으로 이동
 * - 하는 기본적인 MVC 패턴 예제
 */

@Slf4j
// Lombok 기능
// : log.info() / log.error() / log.debug()
//  같은 로그 메서드 자동으로 사용할 수 있게 하는 어노테이션
//  private static final Logger log = LoggerFactory.getLogger(HomeController.class);
//  -> 내부적으로 생성되는 코드
@Controller
// Spring MVC 컨트롤러임을 표시
// : Spring 이 이 클래스를 웹 요청 처리 담당으로 등록

public class HomeController {
    
    private final BoardService boardService;

    public HomeController(BoardService boardService) {
        this.boardService = boardService;
    }
    // 의존성 주입(생성자 주입)
    // : BoardService -> 서비스 계층
    // : HomeController -> 컨트롤러 계층
    //  MVC 패턴에서 Controller -> Service -> DAO 순서로 호출
    // Spring 이 BoardService 구현체를 자동으로 넣어줌 (=의존성 주입 DI)
    @RequestMapping(value = "/", method=RequestMethod.GET)
    // "/" GET 요청 처리
    // : 브라우저에서 localhost:8080/ 이런 요청 오면 실행됨
    //  GET 방식만 허용
    public String home() {
        // 게시글 목록 요청
        List<Board> boardList = null;
        try {
            boardList = boardService.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 게시글 목록 가져오기
        // 1. 서비스에게 "게시글 목록 줘!" 요청
        // 2. 지금은 BoardDAO 가 가짜 데이터 준다고 했으므로
        // 3. 에러가 나도 서버 죽지 말라고 try-catch 사용
        // 실무에서는!
        // -> try-catch 대신, AOP 나 @ControllerAdvice 처리

        // 게시글 조회 요청
        Board board = null;
        try {
            board = boardService.select(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 게시글 하나 조회하기
        // 1. 게시글 번호 1번 조회 요청
        // 2. try-catch 로 예외 방지
        return "index";
        // /templates/index.html
        // : 정확한 위치는 ViewResolver 설정에 따라 다름
        // 즉, 요청 끝 -> index 페이지 로 이동
    }

    /**
     * 초보자를 위한 그림으로 이해!
     * 사용자 (브라우저)
            ↓ 주소접속 "/"
        Controller (HomeController)
            ↓ boardService.list()
        Service (BoardService)
            ↓ boardDAO.list()
        DAO (BoardDAO)
        → 가짜 데이터 생성 후 반환
     */
    
}
