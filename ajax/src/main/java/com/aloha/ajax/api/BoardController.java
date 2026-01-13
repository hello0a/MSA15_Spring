package com.aloha.ajax.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.ajax.dto.Board;
import com.aloha.ajax.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
// CORS 허용
// : 다른 주소에서도 이 API 호출할 수 있게 해주는 설정
// ex) React 는 localhost:3000, 스프링 서버는 localhost:8080
//      -> 서로 포트가 다를 때 기본적으로 호출이 막힘 (=CORS 에러)
// "*" : 모든 곳에서 호출 허용 -> 누구든지 이 API 써도 돼!
@Slf4j
// @Controller     // MVC Controller : view 반환
@RestController // REST API Controller : data 반환 -> 기본적으로 @ResponseBody 적용
// : 웹페이지 대신 JSON 데이터 반환하는 컨트롤러
// -> @Contoller + @ResponseBody 
// 즉, 리턴값을 웹페이지 HTML 아닌 JSON, 문자열, 객체 자체로 응답!
@RequiredArgsConstructor
// : final 변수(BoardService)를 자동으로 생성자 주입해줌
@RequestMapping("/api/boards")
// : 기본 주소 /api/boards 로 시작함을 설정
public class BoardController {
    
    private final BoardService boardService;
    // 게시글 등록/조회/수정/삭제의 실제 로직 수행하는 서비스
    // : final -> 반드시 주입되어야 하는 중요한 객체

    // sp-crud : CRUD 컨트롤러 메서드 자동완성 (확장자 spring code generator)
    //          그대로 tab 누르면 하나씩 키워드 변경 가능!

    // @ResponseBody : @RestController 에서 이미 포함하고 있으므로 생략 가능!
    @GetMapping()
    public ResponseEntity<?> getAll() {
    // 게시글 전체 목록을 돌려주는 메서드
    // : ResponseEntity<?> -> 어떤 타입이든 담을 수 있는 응답 객체
        try {
            List<Board> board = boardService.list();
            return new ResponseEntity<>(board, HttpStatus.OK);
            // : 가져온 목록을 응답으로 돌려주는 것
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{no}")
    public ResponseEntity<?> getOne(@PathVariable("no") Integer no) {
    // {no} 값을 URL 에서 꺼내서 Integer no 변수에 넣어줌
    // : @PathVariable 그 역할 수행
        try {
            Board board = boardService.select(no);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Board board) {
    // @RequestBody 
    // : 클라이언트가 보낸 JSON 데이터를 객체로 자동 전환
        try {
            boolean result = boardService.insert(board);
            if (!result) {
                return new ResponseEntity<>("FAIL",HttpStatus.BAD_REQUEST);
            }
            // insert 요청 성공했지만, 필수 정보가 누락되어 등록 안되는 경우 (400)
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Board board) {
        try {
            boolean result = boardService.update(board);
            if ( !result ) {
                return new ResponseEntity<>("FAIL",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{no}")
    public ResponseEntity<?> destroy(@PathVariable("no") Integer no) {
        try {
            boolean result = boardService.delete(no);
            if (!result) {
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
/**
 * @RequestBody : JSON -> 객체(Java 객체)
 * @ResponseBody : 객체(Java 객체) -> JSON
 * 
 * # 예시 #
 * 
 * 1. RequestBody (요청 들어올 때)
 * {
  "title": "Hello",
  "content": "내용입니다"
    }
 * -> 프론트에서 보낸 JSON
 * @PostMapping("/create")
    public String create(@RequestBody Board board) {
 * -> 스프링에서 받는 객체

 * 2. ResponseBody (응답 보낼 때) / RestController
 * return new Board(3, "제목", "내용");
 * -> Controller 가 반환한 Java 객체
 * {
    "no": 3,
    "title": "제목",
    "content": "내용"
    }
 * -> 스프링 알아서 JSON 바꿔서 보냄
 * | 항목    | @RequestBody   | @ResponseBody  |
    | -----  | -------------- | -------------- |
    | 방향    | 클라이언트 → 서버 | 서버 → 클라이언트 |
    | 변환    | JSON → Java 객체 | Java 객체 → JSON |
    | 사용 위치 | 메서드 파라미터 | 메서드 리턴값     |
    | 필요 조건 | JSON으로 요청   | JSON으로 응답    |
 */
