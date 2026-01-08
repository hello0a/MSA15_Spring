package com.aloha.spring_mvc.service;

import org.springframework.stereotype.Service;

import com.aloha.spring_mvc.dao.UserDAO;
import com.aloha.spring_mvc.dto.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
 
    private final UserDAO userDAO;

    @Override
    public boolean signup(User user) {
        int result = userDAO.signup(user);
        return result > 0;
    } 
}
