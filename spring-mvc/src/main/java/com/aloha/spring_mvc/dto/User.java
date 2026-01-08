package com.aloha.spring_mvc.dto;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class User {
    private Long no;
    private String id;
    private String username;
    private String password;
    private String name;
    // LocalDate 타입
    // private LocalDate birth;

    // Date 타입
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
}
