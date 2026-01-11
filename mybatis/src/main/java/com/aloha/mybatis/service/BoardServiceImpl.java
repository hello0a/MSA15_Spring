package com.aloha.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.mybatis.dto.Board;
import com.aloha.mybatis.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
// 서비스 역할임을 spring 에게 알리는 기능
// : spring 이 자동으로 객체(빈) 만들어 줌
@RequiredArgsConstructor
// final 필드 자동으로 생성자 주입
// : 개발자가 생성자 코드 직접 안 써도 됨
public class BoardServiceImpl implements BoardService{
// BoardService 인터페이스의 실제 실행 버전
// : 인터페이스의 모든 메서드 반드시 구현!
    private final BoardMapper boardMapper;
    // 필드 (Mapper 주입)
    // : DB와 연결된 Mapper 를 필요로 하기 때문에 final 선언
    // -> @RequiredArgsContructor 덕분에 자동 생성자 주입
    // -> Service -> Mapper -> XML -> DB 흐름에서 Mapper 반드시 필요!

    @Override
    public List<Board> list() throws Exception {
        List<Board> list = boardMapper.list();
        return list;
    }
    // 게시글 목록
    // : Mapper 의 SQL 실행 결과(게시글들) 받아서 Controller 에게 전달
    // -> DB 에서 여러 개 가져오므로 List 형태
    // -> Contoller 가 화면에 뿌릴 데이터 준비!

    @Override
    public Board select(Integer no) throws Exception {
        Board board = boardMapper.select(no);
        return board;
    }
    // 게시글 상세 조회
    // : 특정 번호(no) 게시글 1개 조회
    // -> Mapper 의 SELECT 결과 그대로 반환

    @Override
    public boolean insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        return result > 0;
    }
    // 등록
    // : Mapper 는 INSERT 실행 후 영향받은 행 개수를 int로 리턴
    // -> 사용자가 이해하기 쉬운 boolean 로 바꿔서 Service 에서 반환
    // -> Service 는 실제 DB 숫자를 의미있는 값 으로 바꿔주는 역할!
    
    @Override
    public boolean update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result > 0;
    }
    // 수정
    // : 수정된 행의 개수 반환
    
    @Override
    public boolean delete(Integer no) throws Exception {
        int result = boardMapper.delete(no);
        return result > 0;
    }
    // 삭제
    // : 특정 번호 게시글 삭제
}

/**
 * 최종 요약
 * 1. Controller : 요청 받기 / 화면으로 데이터 전달
 * 2. Service : 비즈니스 로직(검증, 처리) + Mapper 에서 받은 숫자 의미있는 값으로 반환
 * 3. Mapper : SQL 실행만 담당
 * 4. XML (SQL) : 실제 쿼리 적힌 파일
 * 5. DB : 데이터 저장 장소
 */
