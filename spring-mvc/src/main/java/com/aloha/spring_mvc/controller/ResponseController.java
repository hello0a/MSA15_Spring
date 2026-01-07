package com.aloha.spring_mvc.controller;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aloha.spring_mvc.dto.Board;
import com.aloha.spring_mvc.dto.Person;
import com.aloha.spring_mvc.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ResponseController {

    private final BoardService boardService;

    /**
     * void
     * : 요청 경로와 같은 이름의 뷰 응답
     * 요청 경로 : /response/index
     * 응답      : /response/index.html
     * @return
     */
    @GetMapping("/response/index")
    public void response() {
        log.info("void 타입 - /response/index");
        log.info("/response/index.html 뷰 응답");
    }
    
    /**
     * String
     * : 뷰 이름을 반환값으로 지정하여 뷰 응답
     * 요청 경로 : /response/view
     * 응답      : /response/index.html
     * @return
     */
    @GetMapping("/response/view")
    public String responseView() {
        log.info("String 타입 - /response/view");
        log.info("/response/index.html 뷰 응답");
        return "response/index"; // 응답할 뷰 이름 지정
    }
    
    /**
     * ModelAndView
     * : model 과 view 를 동시에 처리 (데이터 등록과 화면 지정)
     * @param mv
     * @return
     */
    @GetMapping("/response/model/view")
    public ModelAndView responseModelAndView(ModelAndView mv) {
        log.info("ModelAndView 타입 - /response/model/view");
        log.info("/response/index.html 뷰 응답");
        // 뷰 지정
        mv.setViewName("response/index");
        // 모델 등록
        Person person = new Person();
        person.setName("김조은");
        person.setAge(20);
        mv.addObject("person", person);
        mv.addObject("message", "ModelAndView 데이터...");
        return mv;
    }

    /**
     * 클래스
     * - 요청 경로 : /response/data/board
     * - 응답      : board (XML/JSON) -> 에러 500 정상
     * @ResponseBody : 응답하는 데이터를 본문(body) 에 지정하는 어노테이션
     * * Accept 헤더가 기본적으로 html, xml 우선순위라서 XML 로 응답
     * @return
     * @throws Exception
     * 
     * 500 해결 -> XML 변환 라이브러리 추가 (build.gradle)
     * , produces = "application/xml" -> 없어도 기본 xml
     * json 은 이미 Spring web 에 포함되어 있음
     */
    @ResponseBody
    @GetMapping(value = "/response/data/board", produces = "application/xml")
    public Board responseBoard() throws Exception {
        Board board = boardService.select(1L);
        return board;
    }

    /**
     * JSON 으로 직접 데이터를 세팅하여 응답하기
     * Board -> JSON
     * @throws Exception 
     */
    @ResponseBody
    @GetMapping("/response/data/board/json")
    public void responseJSONBoard(HttpServletResponse response) throws Exception {
        Board board = boardService.select(1L);
        response.setContentType("application/json");
        // 객체를 JSON 문자열로 반환
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(board);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    /**
     * 컬렉션
     * @return
     * @throws Exception
     * produces = "application/json"
     * - Accept : application/xml 요청이 와도, json 으로 응답 가능
     */
    @ResponseBody
    @GetMapping(value = "/reponse/data/board/list", produces = "application/json")
    public List<Board> reponseBoardList() throws Exception {
        List<Board> list = boardService.list();
        return list;
    }

    /**
     * 컬렉션 (Map)
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/response/data/map", produces = "application/json")
    public Map<String, Board> responseMap() {
        Map<String, Board> map = new HashMap<>();
        map.put("board1", new Board(1L, "A", "B", "C"));
        map.put("board2", new Board(2L, "A", "B", "C"));
        map.put("board3", new Board(3L, "A", "B", "C"));
        return map;
    }

    /**
     * ResponseEntity<Void>
     * * ResponseEntity
     * : 응답 헤더, 본문, 상태코드 등 지정하는 객체
     * * Void
     * : 헤더, 상태코드 지정하여 응답 (본문 제외)
     * * HttpStatus 열거타입(ENUM)
     * : 상태코드를 가지고 있는 열거타입
     * - OK                     :200
     * - CREATED                :201
     * - NOT_FOUND              :404
     * - INTERNAL_SERVER_ERROR  :500
     * * ResponseBody 생략 가능!
     * : 컬렉션, 클래스 반환 시 응답 본문 그대로 담기 위해 사용했지만,
     *      ResponseEntity 사용 시 그 역할을 해주기 때문에 어노테이션 사용할 필요 없음
     *      -> 뷰 해석 단계를 건너뛰고, 응답 본문에 직접 처리
     * @return
     */
    @GetMapping("/response/data/entity/void")
    public ResponseEntity<Void> responseEntityVoid() {
        // 방법1 - static 방식
        // return ResponseEntity.ok().build();
        // 방법2 - instant 방식
        return new ResponseEntity<>(HttpStatus.OK);
        // 지정한 HttpStatus 상태로 변경됨
    }
    
    @GetMapping("/response/data/entity/string")
    public ResponseEntity<String> responseEntityString() {
        // return new ResponseEntity<>(응답본문, 상태코드);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/response/data/entity/board/list")
    public ResponseEntity<List<Board>> responseEntityBoardList() throws Exception {
        List<Board> boardList = boardService.list();
        // return new ResponseEntity<>(응답본문, 상태코드);
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @GetMapping("/response/data/entity/map")
    public ResponseEntity<Map<String, Board>> responseEntityBoardMap() throws Exception {
        List<Board> boardList = boardService.list();
        Map<String, Board> map = new HashMap<>();
        int i = 1;
        for (Board board : boardList) {
            map.put("board" + i++, board);
        }
        // return new ResponseEntity<>(응답본문, 상태코드);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * ResponseEntity<?>
     * : 타입 매개변수를 제네릭으로 지정하면, 응답 타입을 유연하게 사용 가능
     * @return
     * @throws Exception
     */
    @GetMapping("/response/data/entity/random")
    public ResponseEntity<?> responseEntityRandom() throws Exception {
        List<Board> boardList = boardService.list();
        Map<String, Board> map = new HashMap<>();
        int i = 1;
        for (Board board : boardList) {
            map.put("board" + i++, board);
        }
        // return new ResponseEntity<>(응답본문, 상태코드);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    /**
     * 파일 다운로드
     * @param param
     * @return
     * @throws UnsupportedEncodingException 
     */
    @GetMapping("/response/data/file")
    public ResponseEntity<byte[]> responseFile(HttpServletRequest request) throws UnsupportedEncodingException {
        // 파일 입력
        // upload\test.png (지정된 경로를 찾을 수 없습니다)
        // String filePath = request.getSession().getServletContext().getRealPath(path);
        String path = "/upload/test.png"; // resources/upload/test.png ..? 선생님은 upload.. 나는 /upload.. 인데 된다..
        String fileName = "test.png";
        
        byte[] fileData = null;
        try {
            // 파일 입력
            // 아래 코드로 대체 (경로 찾기)
            // FileInputStream fis = new FileInputStream(filePath);
            ClassPathResource resource = new ClassPathResource(path);

            // 파일을 바이트코드로 변환
            // fileData = FileCopyUtils.copyToByteArray(fis);
            fileData = resource.getInputStream().readAllBytes();

            // 실무에서는 위의 코드로 사용X
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        fileName = new String( fileName.getBytes("UTF-8"));
        headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        // return new ResponseEntity<byte[]>(응답본문, 헤더, 상태코드);
        return new ResponseEntity<byte[]>(fileData, headers, HttpStatus.OK);
    }
    
    
}