package com.aloha.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.mybatis.dto.Board;
import com.aloha.mybatis.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * [GET] - /board/list : 게시글 목록 화면
 * [GET] - /board/detail : 게시글 조회 화면
 * [GET] - /board/create : 게시글 등록 화면
 * [POST] - /board/create : 게시글 등록 처리
 * [GET] - /board/update : 게시글 수정 화면
 * [POST] - /board/update : 게시글 추정 처리
 * [POST] - /board/delete : 게시글 삭제 처리
 */

/**
 * Controller
 * : 웹 브라우저 요청 들어오면 어떤 서비스로 넘길지, 처리 후 어떤 화면으로 이동할지 결정
 */
@Slf4j      // 로그 어노테이션
@Controller // 컨트롤러 빈으로 등록
@RequestMapping("/board")   // 클래스 레벨 요청 경로 매핑
// 컨트롤러에서 처리할 기본 URL 앞부분
// : /board/ .. 로 시작하는 주소만 처리
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;
    // 게시판 기능 처리하는 서비스 객체 주입받기
    // : DB 까지 직접 가지 않고 반드시 서비스 부탁

    /**
     * 게시글 목록 화면
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    // /barod/list 로 GET 요청 오면 이 메서드 실행하라!
    public String list(Model model) throws Exception {
    // 화면에 전달할 메디엍를 담는 Model 사용
        // 데이터 요청
        List<Board> list = boardService.list();
        // 모델 등록
        model.addAttribute("list", list);
        // addAttribute
        // 화면(view)에게 데이터 전달하는 메서드
        // "list" : view 에서 사용할 이름으로, HTML/JSP/Thymeleaf 화면에서 사용할 변수 이름
        // list : Controller 안에서 사용하는 진짜 데이터
        // 뷰 지정
        return "board/list";
    }

    /**
     * 게시글 조회 화면
     * - /board/detail?no=
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/detail")
    public String detail(
        @RequestParam("no") Integer no,
        Model model
        // @RequestParam
        // : HTTP 요청에서 전달된 파라미터 값을 메서드의 매개변수로 바인딩해주는 Spring MVC 어노테이션
        // -> name 과 연결
        //      폼 name="no" 을 spring 은 "no" 라는 이름의 파라미터 찾아서 어노테이션("no") 에 넣음
    ) throws Exception {
        // 데이터 요청
        Board board = boardService.select(no);
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰 지정
        return "board/detail";
    }

    /**
     * 게시글 등록 화면
     * @return
     */
    @GetMapping("/create")
    public String create() {
        return "board/create";
    }
    /**
     * 게시글 등록 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/create")
    public String create(Board board) throws Exception {
        // 데이터 요청
        boolean result = boardService.insert(board);
        // 리다이렉트
        // 데이터 처리 성공
        if (result) {
            return "redirect:/board/list";
            // redirect
            // : 서버가 다른URL로 다시 요청해! 하고 브라우저에게 지시하는 것
            // -> 현재 요청 처리 끝나면
            // -> 서버가 클라이언트에게 "/board/list" 로 다시 가서 새로 요청해!
            // -> 브라우저가 자동으로 /board/list 로 GET 요청 다시 보냄
            // -> URL 변경되고, 데이터 전달 불가능(새 요청)

            // forward
            // : 서버 내부에서 요청 전달
            // -> URL 변환 없고, 데이터 전달 가능(request 유지)
            // ex) jsp 로 값 넘길 때

            // 왜 등록 후 redirect?
            // : 새로고침하면 중복 등록X (새요청)
        }
        // 데이터 처리 실패
        return "redirect:/board/create?error";
    }
    

    /**
     * 게시글 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(
        @RequestParam("no") Integer no,
        Model model
    ) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "board/update";
    }
    /**
     * 게시글 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String update(Board board) throws Exception {
        // 데이터 요청
        boolean result = boardService.update(board);
        // 리다이렉트
        // 데이터 처리 성공
        if (result) {
            return "redirect:/board/list";
        }
        // 데이터 처리 실패
        int no = board.getNo();
        return "redirect:/board/update?no=" + no + "&error";
    }

    /**
     * 게시글 삭제 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Integer no) throws Exception {
        // 데이터 요청
        boolean result = boardService.delete(no);
        // 리다이렉트
        // 데이터 처리 성공
        if (result) {
            return "redirect:/board/list";
        }
        // 데이터 처리 실패
        return "redirect:/board/delete?no=" + no + "&error";
    }

    
    // No static resource board/board/list.
    // 404 에러 : 리다이렉트 할 때 board/list만 지정해서 발생했으므로 앞에 / 붙이기
}
