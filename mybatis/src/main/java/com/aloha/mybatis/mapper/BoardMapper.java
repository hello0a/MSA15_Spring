package com.aloha.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.mybatis.dto.Board;

// Mapper.xml에서 지정한 id 와 일치하는 메서드명 지정하기
// xml 에 있는 파라미터가 연결되어 값이 매핑된다!
// ex) 파라미터 Integer no 와 #{no}

/**
 * BaordMapper 설명
 * : XML 에 있는 SQL 을 자바 코드에서 호출하는 통로(다리)
 * -> MyBatis 가 자동으로 이 인터페이스와 XML(SQL) 연결해줌!
 */
@Mapper
public interface BoardMapper {
    // 게시글 목록
    List<Board> list() throws Exception;
    // list() 부르면
    // : XML <select id="list"> SQL 실행
    // -> List<Board> 반환으로 여러 개 게시글 가져옴

    // 게시글 조회
    Board select(Integer no) throws Exception;
    // select(3) 번호 보내면
    // : XML <select id="select"> 실행
    // -> Board 객체 1개 조회

    // 게시글 등록
    int  insert(Board board) throws Exception;
    // 새글을 DB에 넣는 기능
    // 반환 값 int 인 이유
    // : DB에 실제로 저장된 행 개수(보통 1) 반환
    // -> 0이면 실패!

    // 게시글 수정
    int  update(Board board) throws Exception;
    // 수정할 게시글의 내용(title, writer, content) 보내면
    // : XML UPDATE 문 실행
    // -> 몇 개 수정됬는지 int로 알려줌 (0 실패 / 1 성공)

    // 게시글 삭제
    int  delete(Integer no) throws Exception;
    // 번호(no) 로 게시글 삭제
    // : 위와 마찬가지
}
