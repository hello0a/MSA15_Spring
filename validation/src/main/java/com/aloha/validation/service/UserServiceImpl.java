package com.aloha.validation.service;

import org.springframework.stereotype.Service;

import com.aloha.validation.dto.Users;
import com.aloha.validation.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public boolean insert(Users user) throws Exception {
        int result = userMapper.insert(user);
        return result > 0;
    }
    
}
