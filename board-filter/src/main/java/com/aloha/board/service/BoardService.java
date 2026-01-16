package com.aloha.board.service;

import java.util.List;

import com.aloha.board.dto.Board;
import com.aloha.board.dto.Pagination;
import com.aloha.board.dto.Params;
import com.github.pagehelper.PageInfo;

public interface BoardService {
  // 게시글 목록
  List<Board> list() throws Exception;
    // 페이지 목록 (추가) - 직접 구현
  List<Board> page(Pagination pagination) throws Exception;
    // 페이지 목록 (추가2) - PageHelper
  PageInfo<Board> page(int page, int size) throws Exception;
    // 페이지 보기 (추가3) - filter
  PageInfo<Board> page(int page, int size, int count) throws Exception;
    // 페이지 보기 (추가3) - 페이징, 정렬
  PageInfo<Board> page(Params params) throws Exception;
  // 게시글 조회
  Board select(Integer no) throws Exception;
  // 게시글 등록
  boolean insert(Board board) throws Exception;
  // 게시글 수정
  boolean update(Board board) throws Exception;
  // 게시글 삭제
  boolean delete(Integer no) throws Exception;
}
