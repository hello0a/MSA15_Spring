package com.aloha.product_rest.dto;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Alias("Base")      // 별칭 (MyBatis package 생략용 - Mapper 에서 사용)
public class Base {
    
    private Long no;            // PK
    @Builder.Default            // 빋더 패턴으로 생성 시, 기본값 UUID 설정
    private String id = UUID.randomUUID().toString();          // UK
    private Date createdAt;     // 등록일시
    private Date updatedAt;     // 수정일시

    public Base() {
        this.id = UUID.randomUUID().toString();
    }
    
    public Base(Long no, String id, Date createdAt, Date updatedAt) {
        this.no = no;
        this.id = UUID.randomUUID().toString();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
