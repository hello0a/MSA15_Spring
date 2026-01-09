package com.aloha.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aloha.board.dto.Files;
import com.aloha.board.dto.ParentTable;

public interface FilesService {
  // 파일 목록
  List<Files> list() throws Exception;
  // 파일 조회
  Files select(Integer no) throws Exception;
  Files selectById(String id) throws Exception;
  // 파일 등록
  boolean insert(Files board) throws Exception;
  // 파일 수정
  boolean update(Files board) throws Exception;
  boolean updateById(Files board) throws Exception;
  // 파일 삭제
  boolean delete(Integer no) throws Exception;
  boolean deleteById(String id) throws Exception;
  // 파일 업로드 (추가)
  int upload(List<MultipartFile> files, ParentTable parentTable, Integer parentNo) throws Exception;
}
