package com.aloha.spring_mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller                 // Controller 로 지정하고 빈 등록
@RequestMapping("/request") // [클래스 레벨 요청경로 매핑] : /request/~
public class RequestController {
    
    // 컨트롤러 메서드
    // 요청 경로 매핑
    /**
     * @RequestMapping : 요청 경로 매핑
     * - 요청 : /request/board
     * - 응답 : /request/board.html
     * @return
     */

    // class 레벨에서 매핑 안한 경우
    // @RequestMapping(value = "/request/board", method=RequestMethod.GET)

    // class 레벨에서 매핑 한 경우
    // @RequestMapping(value = "/board", method=RequestMethod.GET) -> GET 방식 기본 옵션으로, 생략 가능

    // 더 줄여보자!
    // @RequestMapping("/board")
    @GetMapping("/board")
    public String request() {
        log.info("[GET] - /request/board");
        return "request/board";
    }

    /**
     * 경로 패턴 매핑
     * @param no
     * @return
     */
    // @RequestMapping(value = "/board/{no}", method=RequestMethod.GET)
    @GetMapping("/board/{no}")
    public String requestPath(@PathVariable("no") Long no) {
        log.info("[GET] - /request/board/{no}");
        log.info("no : {}", no);
        return "request/board";
    }

    /**
     * 요청 메서드 매핑
     * @return
     */
    // @RequestMapping(value = "/board", method=RequestMethod.POST)
    // 요청 click -> PostMapping 실행 -> redirect -> GetMapping 실행
    // 번호 입력 추가 -> PostMapping 실행 -> @RequestParam -> redirect -> GetMapping 실행

    // Get방식 -> param : url 등록 ?no=100
    // Post방식 -> param : body 등록
    // 단, 스프링부트는 body로 데이터를 요청해도 파라미터로 받을 수 있도록 자동 바인딩 (맞는말인거임?)

    // required = false -> 사용 시 null 처리 가능!
    // ex) 게시물이 없습니다 
    @ResponseBody
    @PostMapping("/board")
    public String requestPost(@RequestParam(name = "no", required = false) Long no) {
        log.info("[POST] - /request/board");
        log.info("no : {}", no);
        // return "redirect:/request/board/list";
        return "SUCCESS";
    }

    @GetMapping("/board/list")
    public String requestList() {
        return "request/board/list";
    }

    /**
     * 파라미터 매핑
     * @param param
     * @return
     * * params 속성으로 요청 파라미터가 id 인 경우 매핑
     * * /request/board?id=aloha <- id 존재
     * * /request/board?id=aloha&age=20 <- id 존재 & age 존재
     * -> 2개 이상 파라미터는 중괄호로 나열
     */
    // @RequestMapping(value = "/board", method=RequestMethod.GET
    //                 , params = {"id", "age"}
    // )
    @GetMapping(value = "/board", params = {"id", "age"})
    public String requestParams(
        @RequestParam("id") String id, 
        @RequestParam("age") Long age
    ) {
        log.info("[GET] - /request/board?id=" + id + "&age=" + age);
        log.info("id : {}", id);
        log.info("age : {}", age);
        return "request/board";
    }

    /**
     * 헤더 매핑
     * @param param
     * @return
     * * headers = "헤더명=값" 으로 지정하여 헤더를 매핑 조건으로 사용
     * 
     * 중괄호 사용하여 여러개 url 등록 가능
     */

    // thunder 확장 추가
    // 단순 error : http 's' -> s 빼기
    // 400 error  : applica 't' ion -> t 빼먹음
    @ResponseBody   // 반환 값을, 응답 메시지 본문(body)에 직접 지정
    @RequestMapping(value = {"/board", "/board2"}, method=RequestMethod.POST
                    , headers = "Content-Type=application/json"
                    // , headers = {"헤더1", "헤더2"}
    )
    public String requestHeader() {
        log.info("[POST] - /request/board");
        log.info("헤더 매핑...");
        return "SUCCESS";
    }
    
    /**
     * PUT 매핑
     * @param param
     * @return
     */
    @ResponseBody
    // @RequestMapping(value = "/board", method=RequestMethod.PUT)
    @PutMapping("/board")
    public String requestPut() {
        log.info("[PUT] - /request/board");
        return "SUCCESS";
    }

    /**
     * 컨텐츠 타입 매핑
     * @return
     * - Content-Type 헤더의 값으로 매핑
     * - consumes = "컨텐츠타입값"
     */
    // @RequestMapping(value = "/board", method=RequestMethod.POST
    //                 , consumes = "application/xml"
    // )
    @ResponseBody
    @PostMapping(value = "/board", consumes = "application/xml")
    public String requestContentType() {
        log.info("[POST] - /request/board");
        log.info("컨텐츠 타입 매핑...");
        return "SUCCESS - xml";
    }

    /**
     * Accept 매핑
     * @param param
     * @return
     * - Accept 헤더의 값으로 매핑
     * - Accept 헤더 ?
     * : 응답 받을 컨텐츠 타입을 서버에게 알려주는 헤더
     * - produces = "컨텐츠 타입"
     * - application/json -> json 형식으로 받겠다!
     */
    @ResponseBody
    // @RequestMapping(value = "/board", method=RequestMethod.POST
    //                 ,produces = "application/json"
    // )
    // Map<String, String> -> {JSON} 으로 반환 (key : value)
    @PostMapping(value = "/board", produces = "application/json")
    public Map<?, ?>  requestAccept() {
        log.info("[POST] - /request/board");
        log.info("Accept 매핑...");
        Map<String, String> map = new HashMap<>();
        map.put("key1","value1" );
        map.put("key2","value2" );
        return map;
    }
    
    
    
    
}
