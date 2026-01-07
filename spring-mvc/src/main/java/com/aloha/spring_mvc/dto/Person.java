package com.aloha.spring_mvc.dto;

import java.util.List;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;

    private List<String> hobby;
}
