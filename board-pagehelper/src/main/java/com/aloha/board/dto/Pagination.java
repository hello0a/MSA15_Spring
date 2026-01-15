package com.aloha.board.dto;

import lombok.Data;

/**
 * [페이지네이션]
 * 페이지 필수 정보
 * - 페이지 번호    : page
 * - 페이지당 데이터 수 : size
 * - 노출 페이지 수 : count
 * - 전체 데이터 수 :total
 * 
 * 페이지 수식 정보
 * - 시작 번호 : start
 * - 끝 번호 : end
 * - 첫 번호 : first
 * - 마지막 번호 : last
 * - 이전 번호 : prev
 * - 다음 번호 : next
 * - 데이터 순서 번호 : index
 */
@Data
public class Pagination {
    // 페이지 기본값
    private static final long PAGE = 1; // 현재 페이지 번호 기본값
    private static final long SIZE = 10;    // 페이지당 데이터 수 기본값
    private static final long COUNT = 10;   // 노출 페이지 수 기본값

    // 필수 정보
    private long page;      // 현재 번호
    private long size;      // 페이지당 데이터 수
    private long count;     // 노출 페이지 수
    private long total;     // 전체 데이터 수

    // 수식 정보
    private long start;     // 시작 번호
    private long end;     // 끝 번호
    private long first;     // 첫 번호
    private long last;     // 마지막 번호
    private long prev;     // 이전 번호
    private long next;     // 다음 번호
    private long index;     // 순서 번호

    /* ##### 생성자 ##### */
    public Pagination() {
        this(0);
    }
    // 기본 생성자
    // : this(0) -> 다른 생성자 Pagination(long total) 호출
    // 즉, 기본값 total = 0 으로 초기화 의미
    // -> 전체 게시글 수 0개 로 설정
    // ## this(..) : 내 다른 생성자 호출한다 의미
    public Pagination(long total) {
        this(PAGE, total);
    }
    // total(전체 개수) 만 받는 생성자
    // : 게시글이 몇 개(total) 인지만 알고 있을 때 사용
    // -> PAGE 라는 기본 페이지 번호 (보통 1페이지) 를 자동 사용
    // 즉, 1페이지 (total 게시글 수) 로 불러라! 의미
    // Pagination(long page, long total) 호출!
    public Pagination(long page, long total) {
        this(page, SIZE, COUNT, total);
    }
    // page + total 받는 생성자
    // : page 현재 있는 페이지 번호
    //   total  전체 게시글 수
    // -> SIZE : 한 페이지에 몇 개씩 보여줄지
    // -> COUNT : 페이지 번호 한 번에 몇 개씩 보여줄지 
    //      기본값 자동 설정
    // Pagination(long page, long size, long count, long total) 호출!
    public Pagination(long page, long size, long count, long total) {
        this.page = page;
        this.size = size;
        this.count = count;
        this.total = total;
        calc();
    }
    // 모든 값을 직접 받는 완전한 생성자
    // : page -> 현재 페이지 번호 저장
    //   size -> 한 페이지당 보여줄 게시글 개수
    //   count -> 한 번에 보여줄 페이지 수
    //   total -> 전체 게시글 수
    // -> 모든 생성자는 결국 "여기"로 모여서 초기화 수행
    /**
     * 요약하면,
     * 1. 생성자들을 여러개 만들어서 사용자가 필요한 정보만 넣어도 자동으로 처리되게 만들고,
     * 2. this(..) 로 생성자들 연결하여 코드를 중복 없이 깔끔하게 관리 */    
    /** 질문
     * 1. 왜 생성자마다 다음 생성자 호출하는가?
     * : 중복 코드 방지 + 기본값 자동 설정
     * -> 원래 코드라면
     *  public Pagination() {
            this.page = PAGE;
            this.size = SIZE;
            this.count = COUNT;
            this.total = 0;
            calc();
        }
        public Pagination(long total) {
            this.page = PAGE;
            this.size = SIZE;
            this.count = COUNT;
            this.total = total;
            calc();
        }
     *  이렇게 page, size, count, total 초기화 코드와 calc() 매번 반복
     *      유지보수 어려워지고, 실수 가능성 높음
     * 해결법 "생성자 체이닝" 
     *      : 필요한 값만 받아서 단계적으로 넘김 -> 마지막 생성자에서 딱 한번 초기화
     * 결론 
     *      : 중복 초기화 코드 없애기 위해 생성자끼리 인쇄 호출!
     * 
     * 2. 기본 생성자에서 this(0) 넣으면 왜 total 로 들어갈까?
     * : 호출 대상은 오버로딩된 생성자 중 매개변수가 long 하나인 생성자 뿐
     * -> long 값 1개 받는 생성자 찾음
     * -> 기준 : 생성자 이름 / 매개변수 개수 / 타입 동일
     *          public Pagination(long total)
     * 결론
     * : 오버로딩된 생성자 중 long 하나를 받는 생성자 있으므로,
     *   기본 생성자에서 this(0) 쓰면 그 생성자 자동으로 선택
     */
    // 페이징 처리 수식
    public void calc() {
        // 첫 번호
        this.first = 1;
        // 마지막 번호
        this.last = (this.total - 1) / size + 1;
        // 시작 번호
        this.start = ((page-1) / count) * count + 1;
        // 끝 번호
        this.end = ((page-1) / count + 1) * count;
        // 끝 번호와 마지막 번호 중 제일 작은 번호가 끝 번호!
        this.end = Math.min( this.end, this.last);
        // if (this.end > this.last) {
        //     this.end = this.last;
        // }

        // 이전 번호
        this.prev = this.page -1;
        // 다음 번호
        this.next = this.page +1;
        // 데이터 순서 번호
        this.index = (this.page - 1) * this.size;
    }
    // 데이터 개수에 따라 토탈 개수가 달라지는데
    // 이를 기준으로 재계산 필요!
    // -> last 때문 : 데이터에 따라 유동적!
    //          (last는 total 값에 의해 결정됨)
    public void setTotal(long total) {
    // setTotal 메서드
    // : 외부에서 total (전체 데이터 개수) 값 바꿀 때 사용하는 메서드
        this.total = total;
        calc();
    }
    // 비유
    // total = 전체 학생 수
    // calc() = 전체 학생 수에 맞게 편성 다시 계산하는 기능
    // -> total 바꾸고 나서 calc() 꼭 호출해줘야 페이지 계산 맞음!
}
