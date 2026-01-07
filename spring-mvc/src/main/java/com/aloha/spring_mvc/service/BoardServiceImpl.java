package com.aloha.spring_mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.spring_mvc.dao.BoardDAO;
import com.aloha.spring_mvc.dto.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    // 의존성 자동 주입 방법1
    // @Autowired
    // private BoardDAO boardDAO;

    // 의존성 자동 주입 방법2
    private final BoardDAO boardDAO;

    @Override
    public List<Board> list() throws Exception {
        List<Board> list = boardDAO.list();
        return list;
    }

    @Override
    public Board select(Long no) throws Exception {
        Board board = boardDAO.select(no);
        return board;
    }

    @Override
    public boolean insert(Board board) throws Exception {
        return boardDAO.insert(board) > 0;
    }

    @Override
    public boolean update(Board board) throws Exception {
        return boardDAO.update(board) > 0;
    }

    @Override
    public boolean delete(Long no) throws Exception {
        return boardDAO.delete(no) > 0;
    }
    
}
