package com.aloha.spring_aop.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aloha.spring_aop.dto.Board;
/**
 * BoardDAO 클래스
 * : DB 대신 가짜 데이터를 만들어서 반환하는 연습용 DAO
 *   추후, JDBC 나 MyBatis, JPA 사용하면 진짜 DB 조회 코드 포함될 예정
 */
@Repository
// 데이터베이스 관련 작업을 하는 클래스 표시하는 스프링 어노테이션
// : spring 이 자동으로 Bean(객체) 으로 등록하여, 다른 곳에서 @Autowired 로 주입받기 가능
//   예외 처리 변환 기능까지 제공
public class BoardDAO {
    // list()
    // : 게시글 목록 반환 (더미 데이터)
    public List<Board> list() {
        List<Board> boardList = new ArrayList<>();
        // List<Board> 타입의 리스트
        // : 게시글 여러 개 담는 역할
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String id3 = UUID.randomUUID().toString();
        // 랜덤한 UUID(고유한 ID 문자열) 자동 생성
        // : DB의 PK처럼 유일한 값 생성
        boardList.add(new Board(1L, id1,
                         "제목", "내용", "작성자", 
                         new Date(), new Date())
                    );
        // 게시글 임시 데이터로 Board 객체 생성
        // : 리스트에 추가
        boardList.add(new Board(2L, id2,
                         "제목", "내용", "작성자", 
                         new Date(), new Date())
                    );
        boardList.add(new Board(3L, id3,
                         "제목", "내용", "작성자", 
                         new Date(), new Date())
                    );
        return boardList;
    }
    // 역할 : 실제 DB 없이 샘플 게시글 목록을 제공하는 메서드

    public Board select(Long no) {
        // select(no) 
        // : 특정 게시글 하나 반환
        String id = UUID.randomUUID().toString();
        return new Board(no, id,
                         "제목", "내용", "작성자", 
                         new Date(), new Date());
        // 원하는 번호(no) 받아서 랜덤 UUID 부여 후, 글 하나 만들어서 반환
    }
    // 역할 : 글 번호로 글 내용을 조회하는 것처럼 흉내내는 테스트용 메서드

    public int insert(Board board) {
        int result = 0;
        return result;
    }
    
    public int update(Board board) {
        int result = 0;
        return result;
    }
    
    public int delete(Long no) {
        int result = 0;
        return result;
    }
    // insert(), update(), delete() -> 현재 빈 껍데기
    // : 아무 기능 없으나
    //   추후,
    //      intsert() -> DB에 새 게시글 저장
    //      update() -> 기존 게시글 수정
    //      delete() -> 글 번호로 게시글 삭제
    //      result -> 성공 1, 실패 0  값 반환
}
