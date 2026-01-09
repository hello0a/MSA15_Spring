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
        if (files != null) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename(); // 원본파일명
                String path = uploadPath + UUID.randomUUID().toString() + "_" + fileName;
                // 파일 저장 
                File realFile = new File(path);
                byte[] fileDate = file.getBytes();
                FileCopyUtils.copy(fileDate, realFile);
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
                }
                filesMapper.insert(newFile);
            }
        }
        return sortOrder;
    }
    
}
