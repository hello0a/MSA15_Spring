package com.aloha.validation.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Users {
     private Integer no;

    @Size(min = 4, max = 64, message = "아이디는 4자 이상 64자 이하로 입력하세요.")
    private String id;

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(
        // ^ : 시작 / $ : 끝
        regexp = "^[A-Za-z0-9]{6,20}$",
        message = "아이디는 영문과 숫자만 가능하며 6~20자 이내로 작성해야합니다."
    )
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
        // jsp와 다르게 \ (백슬러시) 2번 넣어줘야 넣기!
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "비밀번호는 8자 이상이며, 영문, 숫자, 특수문자 포함해야한다."
    )
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, max = 10, message = "이름은 2~10글자 이내로 작성해야합니다.")
    private String name;

    // @Email : 이미 정규표현식 (이메일) 포함되어 있음
    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    private Date createdAt;
    private Date updatedAt;
}
