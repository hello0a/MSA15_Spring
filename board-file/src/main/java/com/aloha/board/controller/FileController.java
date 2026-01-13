package com.aloha.board.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.board.dto.Files;
import com.aloha.board.service.FilesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
// 서버에 저장된 파일을 브라우저로 보여주는 역할하는 컨트롤러 
    private final FilesService filesService;

    /**
     * 파일 뷰어(썸네일)
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    // {id} : 경로 변수
    public ResponseEntity<Resource> viewFile(@PathVariable("id") String id) throws Exception {
    // URL 경로의 {id} 값을 String id로 받음
    // : 파일을 Resoucre 형태로 브라우저에 반환하는 메서드
        Files file = filesService.selectById(id);
        
        if ( file == null ) {
            return ResponseEntity.notFound().build();
        }
        
        FileSystemResource resource = new FileSystemResource(file.getPath());
        // 실제 서버에 저장된 파일 경로를 기반으로, 파일을 읽는 Resource 객체 생성
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        // DB 에 있지만, 실제 서버에는 파일 없을 수 있음
        // ex) 파일 삭제했는데 DB는 남아있는 경우!
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(resource);
        // CONTENT-DISPOSITION: 브라우저가 파일을 어떻게 처리할지 결정하는 헤더
        // inline : 브라우저 화면에서 바로 보여줘라
        //          이미지 (화면에 표시) / PDF (브라우저 뷰어에서 열림)
        // attachment 였다면, 다운로드 됨
        // contentType : 파일 MIME 타입 설정
        //               ex) image/png -> 올바르게 설정해야 제대로 열림
        // .body(resource) : 아까 만든 파일 Resource 를 응답 본문에 담아서 반환
    }
    /**
     * 부모기준 파일 목록
     * @param parentTable
     * @param parentNo
     * @return
     */
    @GetMapping("/{parentTable}/{parentNo}")
    public ResponseEntity<?> listByParent(
        @PathVariable("parentTable") String parentTable,
        @PathVariable("parentNo") Integer parentNo
    ) {
        try {
            Files file = new Files();
            file.setParentTable(parentTable);
            file.setParentNo(parentNo);
            List<Files> fileList = filesService.listByParent(file);
            return new ResponseEntity<>(fileList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 파일 삭제
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<?,?>> deleteFile(@PathVariable("id") String id) throws Exception {
        boolean result = filesService.deleteById(id);
        if ( !result ) {
            return ResponseEntity.notFound().build();
        }
        Map<String,Object> response = new HashMap<>();
        response.put("SUCCESS", true);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 전체 동작 요약
     * 1. URL 에서 id 받아옴
     * 2. DB에서 파일 정보 조회
     * 3. 실제 서버 파일 확인
     * 4. 브라우저에 파일 바로 보여준(inline)
     * -> 게시판 이미지/썸네일 이
     *      <img src="/files/파일ID">
     */
    
}
