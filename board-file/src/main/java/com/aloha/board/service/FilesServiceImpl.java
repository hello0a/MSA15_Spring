package com.aloha.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.board.dto.Files;
import com.aloha.board.dto.ParentTable;
import com.aloha.board.mapper.FilesMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {

    private final FilesMapper filesMapper;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public List<Files> list() throws Exception {
        List<Files> files = filesMapper.list();
        return files;
    }

    @Override
    public Files select(Integer no) throws Exception {
        Files files = filesMapper.select(no);
        return files;
    }
    
    @Override
    public Files selectById(String id) throws Exception {
        Files files = filesMapper.selectById(id);
        return files;
    }

    @Override
    public boolean insert(Files board) throws Exception {
        int result = filesMapper.insert(board);
        return result > 0;
    }

    @Override
    public boolean update(Files board) throws Exception {
       int result = filesMapper.update(board);
        return result > 0;
    }

    @Override
    public boolean updateById(Files board) throws Exception {
        int result = filesMapper.updateById(board);
        return result > 0;
    }

    @Override
    public boolean delete(Integer no) throws Exception {
        int result = filesMapper.delete(no);
        return result > 0;
    }

    @Override
    public boolean deleteById(String id) throws Exception {
        int result = filesMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public int upload(List<MultipartFile> files, ParentTable parentTable, Integer parentNo) throws Exception {
        int sortOrder = 0;
        // 파일 여러개 업로드했을 때
        // : 첫번째 파일 sortOrder=0
        // : 두번째 파일 sortOrder=1 ,,,
        // -> 순서 저장하기 위한 번호
        if (files != null) {
            for (MultipartFile file : files) {
            // MultipartFile
            // : spring 에서 파일 업로드 처리하는 핵심 인터페이스
            // -> 웹 브라우저에서 사용자가 <input type="file"> 로 파일 업로드하면
            //      Srping MVC 가 자바 객체로 변환해줄 때 쓰는 타입
            // 즉, 클라이언트가 전송한 파일 1개를 담고 있는 객체
                String fileName = file.getOriginalFilename(); // 원본파일명
                String path = uploadPath + UUID.randomUUID().toString() + "_" + fileName;
                // 서버에 저장될 실제 파일 경로

                // 파일 저장 
                File realFile = new File(path);
                // 방금 만든 경로로 빈 파일 객체 생성
                byte[] fileDate = file.getBytes();
                // 실제 파일 내용을 MultipartFile 에서 byte[] 로 꺼냄
                FileCopyUtils.copy(fileDate, realFile);
                // 파일 내용을 실제 서버 경로에 복사(저장)
                // : 이 줄에서 진짜 파일이 서버 디렉토리에 저장됨!

                // DB 저장
                Files newFile = new Files();
                newFile.setParentNo(parentNo);
                newFile.setParentTable(parentTable.value());
                newFile.setName(fileName);
                newFile.setPath(path);
                newFile.setContentType(file.getContentType());
                newFile.setSortOrder(sortOrder++);
                if (sortOrder == 1) {
                    newFile.setIsMain(true);
                    // 첫번째 파일만 대표 이미지 설정
                    // : sortorder=0
                }
                filesMapper.insert(newFile);
            }
        }
        return sortOrder;
    }
    /**
     * upload 요약
     * : 업로드된 파일 서버에 저장하고, Files 테이블에 메타 정보도 저장하는 메서드이며,
     *      파일 개수로 반환
     */
    
}
