package com.aloha.board.service;

import java.util.List;
import java.util.Map;

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
  // 부모 기준 목록
  List<Files> listByParent(Files files) throws Exception;
  // 부모 기준 파일 삭제
  int deleteByParent(Files files) throws Exception;
  // 파일 순서 변경
  boolean updateSortOrder(List<Map<String, Object>> sortOrderList) throws Exception;
  // : 입력 타입 List<Map<String, Object>>
  // -> Map 형태 : 키-값으로 아무 데이터나 넣기 가능
  //              ex) {"id": 5, "sortOrder": 2}
  //              유연하지만 타입 안정성 떨어짐
  //              -> 프론트에서 보낸 JSON 그대로 받을 때 자주 씀!
  boolean updateFileSortOrder(List<Files> fileList) throws Exception;
  // : 입력 타입 List<Files> fileList
  // -> 완전한 객체(DTO) 형태로 받기
  //     업격하고 안전한 데이터 구조 사용하는 방식
}
/** List vs Map
 * 1. List
 * : 순서대로 넣는 데이터 상자
 * -> index (순서) 있어서 배열처럼 인덱스로 꺼내므로, 같은 타입의 객체 묶어서 저장
 * 2. Map
 * : 이름표 (key) 붙여서 저장하는 상자
 * -> key : value 구조로, key 를 순서 없이 꺼내옴(중복 불가)
 * 
 * ## Map 과 HashMap
 * 1. Map 은 인터페이스!
 * -> 혼자서 객체 못 만드므로, new Map<>() 불가능
 * 2. HashMap 은 Map 의 실제 구현체!
 * -> Map 설계도 / HashMap 진짜 집
 * -> Map 사용할 수 있게 구현한 클래스가 HashMap
 * 3. Arraylist 과 List 처럼
 * -> List(인터페이스) / Arraylist 와 LinkedList(구현체)
 * -> Map(인터페이스) / HashMap, LinkedHashMap, TreeMap(구현체)
 * 
 * 번외 : 왜 굳이 HashMap?
 * 1. 가장 빠르고 가볍고 기본 성능 good
 * -> key 해시(hash) 방식으로 저장하여 찾기/넣기/삭제 빠름
 * 2. 순서 상관 없다면 가장 적합
 * -> HashMap 은 입력한 순서 보장X
 * 
 * 
 * 왜 updateSortOrder 에 List<Map> 사용?
 * : 프론트에서 오는 JSON 형식 ->  { "id": 5, "sortOrder": 2 }
 * -> 자바로 받으면
 *    리스트(list) / 각 항목은 key-value 구조(Map)
 * 
 * 최종 정리
 * 1. List = 여러 개 나열한 데이터
 * 2. Map = key-value 구조 데이터
 * 3. List<Map> = 여러 개의 key-value 객체들을 담은 리스트
 */
