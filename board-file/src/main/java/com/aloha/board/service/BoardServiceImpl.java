package com.aloha.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.board.dto.Board;
import com.aloha.board.dto.ParentTable;
import com.aloha.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardMapper boardMapper;
  private final FilesService filesService;

  @Override
  public List<Board> list() throws Exception {
    List<Board> list = boardMapper.list();
    return list;
  }

  @Override
  public Board select(Integer no) throws Exception {
    Board board = boardMapper.select(no);
    
    return board;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    // 게시글 등록
    int result = boardMapper.insert(board);
    int parentNo = board.getNo();
    // Mapper.xml : <insert id="insert" useGeneratedKeys="true" keyProperty="no">
    // INSERT 성공하면 DB 가 생성한 primary key(no) 를 자동으로 board.no 에 채워줌!
    // -> INSERT 이후에 board.getNo() 하면, DB에서 생성된 게시글 번호 들어있게 됨

    // 파일 업로드
    int fileResult = filesService.upload(board.getFiles(), ParentTable.BOARD, parentNo);
    // 1. board.getFile() : 업로드한 파일 목록
    // 2. parentTable : ParentTable.BOARD
    //    -> 파일이 어느 테이블에 속하는지 알려줌
    //    -> parent_table = 'board'
    // 3. parentNo : 게시글 번호
    //    -> 파일이 어떤 게시글에 속하는지 알려줌
    //    -> parent_no = 게시글 번호
    // 결과 Files 테이블
    // : file(이미지1) parent_table(board) parent_no(10)
    log.info("파일 업로드 - {}개 파일 등록", fileResult);
    return result > 0;
  }
  
  @Override
  public boolean update(Board board) throws Exception {
    int result = boardMapper.update(board);
   
    return result > 0;
  }
  
  @Override
  public boolean delete(Integer no) throws Exception {
    int result = boardMapper.delete(no);
    return result > 0;
  }
  
}

