package com.aloha.validation.service;

import com.aloha.validation.dto.Users;

public interface UserService {
    boolean insert(Users user) throws Exception;
}
