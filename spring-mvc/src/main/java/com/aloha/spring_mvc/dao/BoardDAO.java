package com.aloha.spring_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aloha.spring_mvc.dto.Board;

/**
 * BoardDAO 란?
 * : DAO = Data Access Object
 * 즉, DB에 접근해서 데이터 가져오고, 저장하고, 수정하고, 삭제하는 역할 (현재 구조)
 */

@Repository
// Spring 에게 이 클래스는 DAO(데이터 접근 객체) 라고 알려줌
// : Spring 이 자동으로 객체 생성(빈 등록)
// -> 서비스 클래스에서 @Autowired 로 주입받기 가능
// 비유하자면,
//      1. DAO = 데이터 담당 직원
//      2. @Repository = 이 직원은 데이터 부서 직원임! 표시
public class BoardDAO {
    
    public List<Board> list() {
        List<Board> list = new ArrayList<>();
        list.add(new Board(1L, "제목01", "작성자01","내용01"));
        list.add(new Board(2L, "제목02", "작성자02","내용02"));
        list.add(new Board(3L, "제목03", "작성자03","내용03"));
        return list;
        // 목록 가져오기 list
        // 1. ArrayList 하나 만들고,
        // 2. 새로운 Board 객체 3개 만들어 넣고,
        // 3. 그 리스트를 반환하는 메서드
    }
    public Board select(Long no) {
        Board board = new Board(1L, "제목01", "작성자01","내용01");
        return board;
        // 특정 글 한개 조회 select
        // : AOP 테스트용 미니 버전
    }
    public int insert(Board board) {
        int result = 1;
        return result;
        // 글 삽입 insert
        // : 성공하면1, 실패하면 0 반환하는 구조
    }
    public int update(Board board) {
        int result = 1;
        return result;
    }
    public int delete(Long no) {
        int result = 1;
        return result;
    }
}
