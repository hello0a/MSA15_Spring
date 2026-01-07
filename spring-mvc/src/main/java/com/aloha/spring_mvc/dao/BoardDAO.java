package com.aloha.spring_mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aloha.spring_mvc.dto.Board;

@Repository
public class BoardDAO {
    
    public List<Board> list() {
        List<Board> list = new ArrayList<>();
        list.add(new Board(1L, "제목01", "작성자01","내용01"));
        list.add(new Board(2L, "제목02", "작성자02","내용02"));
        list.add(new Board(3L, "제목03", "작성자03","내용03"));
        return list;
    }
    public Board select(Long no) {
        Board board = new Board(1L, "제목01", "작성자01","내용01");
        return board;
    }
    public int insert(Board board) {
        int result = 1;
        return result;
    }
    public int update(Board board) {
        int result = 1;
        return result;
    }
    public int delete(Long no) {
        int result = 1;
        return result;
    }
}
