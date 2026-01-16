package com.aloha.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
// 정렬 파라미터 클래스
public class Params {
    @Builder.Default
    private Integer page = (int) Pagination.PAGE;
    @Builder.Default
    private Integer size = (int) Pagination.SIZE;
    @Builder.Default
    private Integer count = (int) Pagination.COUNT;
    private Integer index;
    private List<String> sortBy;        // 정렬 기준 컬럼 ("title","writer","created_at" 등)
    private List<String> sortOrder;     // 정렬 순서 ("ASC", "DESC" 등)

    public Params() {
        this.page = (int) Pagination.PAGE;
        this.size = (int) Pagination.SIZE;
        this.count = (int) Pagination.COUNT;
    }
}

