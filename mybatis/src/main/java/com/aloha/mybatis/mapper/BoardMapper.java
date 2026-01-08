package com.aloha.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.mybatis.dto.Board;

// Mapper.xml에서 지정한 id 와 일치하는 메서드명 지정하기
// xml 에 있는 파라미터가 연결되어 값이 매핑된다!
// ex) 파라미터 Integer no 와 #{no}
@Mapper
public interface BoardMapper {
    // 게시글 목록
    List<Board> list() throws Exception;
    // 게시글 조회
    Board select(Integer no) throws Exception;
    // 게시글 등록
    int  insert(Board board) throws Exception;
    // 게시글 수정
    int  update(Board board) throws Exception;
    // 게시글 삭제
    int  delete(Integer no) throws Exception;
}
