package com.aloha.ajax.service;

import java.util.List;

import com.aloha.ajax.dto.Board;

public interface BoardService {
    List<Board> list();
    Board select(Integer no);
    boolean insert(Board board);
    boolean update(Board board);
    boolean delete(Integer no);
}
