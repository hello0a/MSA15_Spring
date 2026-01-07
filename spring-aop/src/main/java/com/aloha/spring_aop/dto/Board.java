package com.aloha.spring_aop.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lombok 라이브러리 제공하는 어노테이션
 * -> 자바 클래스에서 반복적으로 쓰는 코드를 자동으로 만들어주는 도구
 */

@Data
// 한 방에 여러 기능 자동 생성
// 1. getter(값 꺼내는 메서드), setter(값 넣는 메서드)
// 2. toString() (객체를 문자열로 출력할 때 예쁘게 보여주는 메서드)
// 3. equals(), hashCode() (객체 비교할 때 필요한 메서드 자동 생성)
// 4. RequiredArgsConstructor (필드 중 final/@NonNull 있는 필드 생성자 생성)
@NoArgsConstructor
// 파라미터 없는 생성자 자동 생성
// -> 아무것도 받지 않는 기본 생성자
// 기본 생성자 디폴트 but 다른 생성자 만들면 사라지므로 롬복으로 다시 생성
@AllArgsConstructor
// 모든 필드를 파라미터로 받는 생성자
// -> 클래스에 있는 모든 필드를 한 번에 받는 생성자
// 객체 만들고 싶을 때 편리함!
public class Board {
    
    private Long no;
    private String id;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
