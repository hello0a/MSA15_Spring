package com.aloha.spring_aop.service;

import java.util.List;

import com.aloha.spring_aop.dto.Board;

/**
 * BoardService 란?
 * : Controller(웹 요청)와 DAO(DB 작업) 사이에서 중간 역할을 하는 계층 (=Service Layer)
 * -> 비즈니스 로직 담당자
 * 
 * 즉,
 * 1. Controller : 화면 처리만,
 * 2. DAO : DB 처리만,
 * 3. Service : 규칙/로직/흐름 처리 담당
 */

// interface 란?
// : 설계도 -> 이런 기능들을 반드시 구현해야 한다. 라고 정한 것
//  구현체 -> BoardServiceImpl
public interface BoardService {
    //목록
    public List<Board> list() throws Exception;
    // 게시글 여러 개 가져오는 기능
    // : DAO 에서 가져온 리스트를 Controller 에 넘김

    //조회
    public Board select(Long no) throws Exception;
    // 게시글 하나 조회(select)
    // : 글 번호(No)로 글 한개 가져오기
    // ex) 상세보기 기능 해당

    //등록
    public boolean insert(Board board) throws Exception;
    // 게시글 새로 작성하는 기능
    // : 성공하면 true, 실패하면 false
    // ex) DAO 에서 return 값(1 or 0) 받아서 -> boolean 변환 가능

    //수정
    public boolean update(Board board) throws Exception;
    // 기존 게시글 수정하는 기능
    // : 제목, 내용, 작성자 등 바꿀 때 사용

    //삭제
    public boolean delete(Long no) throws Exception;
    // 특정 글 번호의 게시글 삭제
    // :성공, 실패 여부 boolean 으로 반환
} 
/**
 * 왜 Service 계층 필요할까?
 * : Controller 와 DAO 사이에 Service 가 없다면,,,
 * -> Controller 가 DB 로직 직접 수행하게 되어 코드 지저분해지고 유지보수 어려움
 * 
 * Service Layer 역할
 * - DB 트랜잭션 처리
 * - 유효성 검사 (글 길이 체크 등)
 * - 비즈니스 로직 처리
 * - 여러 DAO 조합한 복잡 로직 처리
 * - Controller 깔끔하게 만듦
 * 
 * 초보자용 쉽게 비교하면
 * - Controller = 프론트 직원 (손님 응대)
 * - Service = 매니저 (업무 조율)
 * - DAO = 창고 직원 (DB 작업)
 * 
 * 전체 흐름도
 * Controller -> Service -> DAO -> DB
 */
