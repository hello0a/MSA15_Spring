package com.aloha.spring_mvc.service;

import java.util.List;

import com.aloha.spring_mvc.dto.Board;

// Spring MVC 구조의 핵심 역할
// 해당 클래스의 정체!
// : DAO와 Controller 사이에서 중간 관리자 역할을 하는 계층
// Controller (사용자 요청 처리) -> Service (비즈니스 로직) -> DAO (DB 접근)

// 왜 인터페이스?
// : 구현체를 바꿔 끼우기(테스트/AOP 적용/DB 교체) 쉬움
// ex) BoardServiceImpl
// -> 나중에 캐시 버전, 다른 데이터 소스 버전 등 추가
public interface BoardService {
    
    // 게시글 목록
    List<Board> list() throws Exception;
    // DAO에서 list 가져와서 Controller에 전달

    // 게시글 조회
    Board select(Long no) throws Exception;
    // 글 번호 no 받아서 DB에서 한 개의 게시글 가져오는 기능

    // 게시글 등록
    boolean insert(Board board) throws Exception;
    // 사용자가 입력한 Board 객체를 받아 DB에 저장하는 기능
    // ! DAO는 int 반환 -> 성공1, 실패0
    // ! Service는 boolean 반환 -> '성공 여부'

    // 게시글 수정
    boolean update(Board board) throws Exception;
    
    // 게시글 삭제
    boolean delete(Long no) throws Exception;
}
/**
 * 전체적으로 보면...
 * : Service 계층은 "중간 관리자!"
 * 1. Controller 에서 넘어온 데이터 검증하고,
 * 2. 비즈니스 로직 처리하고,
 * 3. DAO 와 연결해서 DB 처리하고,
 * 4. 트랜잭션 관리 (@Transactional) 하고,
 * 5. 예외 처리!
 * 
 * 
 * ## @Transactional
 * : Spring 에서 트랜잭션(하나의 단위)을 관리하기 위한 핵심 어노테이션
 * -> DB작업 (insert, update 등) 을 트랜잭션으로 묶어서 전부 성공 or 실패(롤백) 시키도록 보장!
 *    DB 변경하는 서비스 로직에서 붙는다.
 */
