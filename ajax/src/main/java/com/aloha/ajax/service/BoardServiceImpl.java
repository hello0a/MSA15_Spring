package com.aloha.ajax.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.ajax.dto.Board;
import com.aloha.ajax.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    
    private final BoardMapper boardMapper;

    @Override
    public List<Board> list() {
        return boardMapper.list();
    }

    @Override
    public Board select(Integer no) {
        return boardMapper.select(no);
    }

    @Override
    public boolean insert(Board board) {
        return boardMapper.insert(board) > 0;
    }

    @Override
    public boolean update(Board board) {
         return boardMapper.update(board) > 0;
    }

    @Override
    public boolean delete(Integer no) {
         return boardMapper.delete(no) > 0;
    }
    
}
