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
@Slf4j
// @Controller     // MVC Controller : view 반환
@RestController // REST API Controller : data 반환 -> 기본적으로 @ResponseBody 적용
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    
    private final BoardService boardService;

    // sp-crud : CRUD 컨트롤러 메서드 자동완성 (확장자 spring code generator)
    //          그대로 tab 누르면 하나씩 키워드 변경 가능!

    // @ResponseBody : @RestController 에서 이미 포함하고 있으므로 생략 가능!
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            List<Board> board = boardService.list();
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{no}")
    public ResponseEntity<?> getOne(@PathVariable("no") Integer no) {
        try {
            Board board = boardService.select(no);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Board board) {
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
