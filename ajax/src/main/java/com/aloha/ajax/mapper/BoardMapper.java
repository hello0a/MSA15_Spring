package com.aloha.ajax.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.ajax.dto.Board;

@Mapper
public interface BoardMapper {
    List<Board> list();
    Board select(Integer no);
    int insert(Board board);
    int update(Board board);
    int delete(Integer no);
}
