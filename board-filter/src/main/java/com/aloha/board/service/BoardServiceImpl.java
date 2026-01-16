package com.aloha.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.board.dto.Board;
import com.aloha.board.dto.Files;
import com.aloha.board.dto.Pagination;
import com.aloha.board.dto.Params;
import com.aloha.board.dto.ParentTable;
import com.aloha.board.mapper.BoardMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
    // 게시글 수정
    int result = boardMapper.update(board);

    // 파일 업로드
    int parentNo = board.getNo();
    int fileResult = filesService.upload(board.getFiles(), ParentTable.BOARD, parentNo);
    log.info("파일 업로드 - {}개 파일 등록", fileResult);
    return result > 0;
  }
  
  @Override
  @Transactional // 트랜잭션 처리 : 2개 이상의 데이터베이스 요청에서 하나라도 실패하면 전체를 롤백한다
  public boolean delete(Integer no) throws Exception {
    // 게시글 삭제
    int result = boardMapper.delete(no);
    // 첨부파일 삭제
    Files file = new Files();
    file.setParentTable(ParentTable.BOARD.value());
    file.setParentNo(no);
    int fileResult = filesService.deleteByParent(file);
    log.info("파일 삭제 - {} 개 파일 삭제", fileResult);
    return result > 0;
  }

  @Override
  public List<Board> page(Pagination pagination) throws Exception {
    // 데이터 수 조회
    long total = boardMapper.count();
    pagination.setTotal(total);
    List<Board> list = boardMapper.page(pagination);
    return list;
  }

  @Override // 추가2
  public PageInfo<Board> page(int page, int size) throws Exception {
    // PageHelper.startPage(현재 번호, 페이지당 데이터 수)
    PageHelper.startPage(page, size);
    List<Board> list = boardMapper.list();

    // PageInfo<DTO> (리스트, 노출 페이지 수)
    PageInfo<Board> pageInfo = new PageInfo<>(list, 10);
    return pageInfo;
  }
  // 추가3 노출 페이지 보기!!! filter
  @Override
  public PageInfo<Board> page(int page, int size, int count) throws Exception {
    // PageHelper.startPage(현재 번호, 페이지당 데이터 수)
    PageHelper.startPage(page, size);
    List<Board> list = boardMapper.list();

    // PageInfo<DTO> (리스트, 노출 페이지 수) -> 10을 count로 변경
    PageInfo<Board> pageInfo = new PageInfo<>(list, count);
    return pageInfo;
  }

  // 추가3 노출 페이지 보기!!! filter
  @Override
  public PageInfo<Board> page(Params params) throws Exception {
    // PageHelper.startPage(현재 번호, 페이지당 데이터 수)
    PageHelper.startPage(params.getPage(), params.getSize());
    // List<Board> list = boardMapper.list();
    // 페이징 처리X, 전체 목록 정렬만
    List<Board> list = boardMapper.listWithParam(params);

    // PageInfo<DTO> (리스트, 노출 페이지 수) -> 10을 count로 변경
    // 정렬 결과 받은 후 페이징 처리O
    PageInfo<Board> pageInfo = new PageInfo<>(list, params.getCount());
    return pageInfo;
  }
  
}

