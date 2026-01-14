package com.aloha.validation.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.validation.dto.Users;

@Mapper
public interface UserMapper {
    // 회원 등록
    int insert(Users user) throws Exception;
}
