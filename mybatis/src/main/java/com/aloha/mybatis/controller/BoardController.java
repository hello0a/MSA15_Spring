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
@Slf4j      // 로그 어노테이션
@Controller // 컨트롤러 빈으로 등록
@RequestMapping("/board")   // 클래스 레벨 요청 경로 매핑
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    /**
     * 게시글 목록 화면
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public String list(Model model) throws Exception {
        // 데이터 요청
        List<Board> list = boardService.list();
        // 모델 등록
        model.addAttribute("list", list);
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
