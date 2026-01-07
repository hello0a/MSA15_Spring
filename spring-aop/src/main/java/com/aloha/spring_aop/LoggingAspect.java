package com.aloha.spring_aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.aloha.spring_aop.dto.Board;

import lombok.extern.slf4j.Slf4j;

/**
 * 해당 클래스 
 * : AOP(관점 지향 프로그래밍) 활용해서 BoardService의 모든 메서드 실행 전/후/예외/반환 값을
 *   자동으로 로그 찍어주는 기능
 * 
 * AOP란?
 * : 서비스 메서드들이 실행될 때마다 로그를 자동으로 남겨주는 클래스
 * -> 서비스 메서드들이 실행되기 전/후/에러 발생 시 자동으로 끼어들어 동작하는 기능
 * 
 * AOP를 현실에서 비유하면?
 * : 가게에서 알바생(BoardService) 일하고 있을 때,
 *   매니저(Aspect) 가 알바생이 일할 때마다 몰래 옆에서 기록하는 기능
 * 1. 일을 시작하면 -> 일 시작하겠습니다! 기록 (@Before)
 * 2. 일 끝나면 -> 일 끝났습니다! 기록 (@After)
 * 3. 손님에게 물건 건네면 -> 결과는 이것입니다! 기록 (@AfterReturning)
 * 4. 일 하다가 실수하면 -> 예외 발생했습니다! 기록 (@AfterThrowing)
 * 5. 일 전체 감시 -> 처음부터 끝까지 내가 감시하겠다! (@Around)
 */


// Aspect

@Slf4j
// log.info(), log.error() 같은 로그 기능 자동 생성
@EnableAspectJAutoProxy // AOP 활성화
// Spring AOP 기능 실제로 활성화
// : 이걸 켜야 @Aspect 동작
// -> Spring 이 Proxy 객체 만들어서 감시 가능하게 만듬
@Component              // 빈 등록
// 이 클래스도 Spring 이 관리하는 객체(Bean) 이다!
// : 스프링이 관리해야 AOP 기능 적용 가능
@Aspect                 // AOP 클래스 지정
// 나는 AOP 기능 담당하는 클래스이다!
// : @Before, @After 같은 기능 사용 가능

public class LoggingAspect {
// Aspect(관점) 역할을 하는 클래스
// : 로그 남기기 라는 기능을 서비스 전체에 적용하기 위해 존재

    // Advice
    // Point Cut : execution (접근제한자 반환타입 패키지.클래스.메서드(파라미터))
    // Target : BoardService*.
    // Join Point : @Before, @After, @Aroung 등


    @Before("execution(* com.aloha.spring_aop.service.BoardService*.*(..))")
    // 메서드 실행 전에 실행되는 기능
    // : BoardService 로 시작하는 모든 서비스 클래스의 모든 메서드 실행 전에
    //   미리 이 Before() 코드를 실행해라!
    // 즉, 서비스가 일을 시작하기 전에, 매니저가 와서 '지금 어떤 메서드 실행할거지?' 하고 기록

    // execution(...) : 적용 범위
    // -> BoardService 로 시작하는 클래스의 모든 메서드에 적용
    // 포인트컷! 어디에 끼어들지 결정하는 조건

    public void before(JoinPoint jp) {
    // JoinPoint : 지금 실행하려는 타겟 메서드의 정보 들어있음
    // -> 메서드 이름, 파라미터, 클래스 이름 등
        // jp.getSignature() : 타겟 메서드 시그니처 정보(반환 타입, 패키지.클래스.메서드) 반환
        // jp.getArgs()      : 타겟 메서드의 매개변수를 반환
        log.info("===============================================");
        log.info("[@Before]######################################");
        log.info("target : " + jp.getTarget().toString());
        // jp.getTarget()
        // : 어떤 객체의 메서드를 실행하려고 했는지 알려줌
        log.info("signature : " + jp.getSignature());
        // jp.getSignature()
        // : 메서드 이름, 반환 타입 같은 메서드 정보
        log.info("args : " + Arrays.toString(jp.getArgs()));
        // jp.getArgs()
        // : 메서드에 전달된 파라미터 값 목록
        // 파라미터 출력
        printParam(jp);
        log.info("===============================================");
    }

   @After("execution(* com.aloha.spring_aop.service.BoardService*.*(..))")
   // 메서드 실행 후에 실행되는 기능
   // : BoardService 안의 모든 메서드가 실행되고 나면
   //   이 after()를 자동으로 실행해라!
   // 즉, 알바생이 일을 마치면, 매니저가 와서 '일 끝났음' 기록
   // -> 정상 종료, 예외 발생 등 메서드가 끝나기만 하면 무조건 실행
    public void after(JoinPoint jp) {
        // jp.getSignature() : 타겟 메서드 시그니처 정보(반환 타입, 패키지.클래스.메서드) 반환
        // jp.getArgs()      : 타겟 메서드의 매개변수를 반환
        log.info("===============================================");
        log.info("[@After]######################################");
        log.info("target : " + jp.getTarget().toString());
        log.info("signature : " + jp.getSignature());
        log.info("args : " + Arrays.toString(jp.getArgs()));
        // 파라미터 출력
        printParam(jp);
        log.info("===============================================");
    } 
    
    // pointcut : 포인트컷 표현식
    // returning : 타겟 메서드의 반환값을 저장할 매개변수명 지정
    @AfterReturning (
    // 정상적으로 끝났을 때만 실행
    // : 메서드가 에러 없이 정상 완료되면 실행하고, 반환 값 result 받앗 ㅓ기록
    // 즉, 손님에게 준 물건이 무엇인지 기록
        pointcut =  "execution(* com.aloha.spring_aop.service.BoardService*.*(..))",
        returning = "result"
    )
    public void AfterReturning(JoinPoint jp, Object result) {
    // result가 null 이 아니면 반환값 출력
        log.info("==============================================");
        log.info("[@AfterReturning] ####################################");
        log.info("target : {}", jp.getTarget());
        log.info("signature : {}", jp.getSignature());
        log.info("args : {}", Arrays.toString(jp.getArgs()));
        // 파라미터 출력
        printParam(jp);
        // 반환값 출력
        if (result != null) {
            log.info("반환값 : {}", result);
        }
        if (result instanceof Board) {
            result = (Board) result;
            log.info("반환값 : {}", result);
        }
        // 반환값이 Board 객체면 형 변환해서 출력
        // 즉, 알바생이 손님에게 무엇을 전달했는지 기록
        log.info("=========================================");
    }

    /**
     * 예외 발생 후 동작
     * @param jp
     * @param exception
     */
    @AfterThrowing(
    // 예외 발생했을 때 실행
    // : 메서드 실행 중에 에러가 터지면 이 코드 실행해라!
    // 즉, 알바생이 일을 하다가 실수하면, 매니저가 여기 에러났다! 라고 기록
    //      BoardServiceImpl.select() 에서
    //      int test = 10 / 0 때문에 무조건 실행
        pointcut = "execution(* com.aloha.spring_aop.service.BoardService*.*(..))",
        throwing = "exception"
        // exception 매개변수로 발생한 예외 받기 가능
    )
    public void AfterThrowing(JoinPoint jp, Exception exception) {
    // 어떤 메서드에서 어떤 에러가 났는지 기록
        log.info("==================================================");
        log.info("[@AfterThrowing] ########################################");
        log.info("target : " + jp.getTarget().toString());
        log.info("signature : " + jp.getSignature());
        log.info( exception.toString() );
        log.info("==================================================");
    }

    /**
    * 파라미터 출력
    * @param jp
    **/
    public void printParam(JoinPoint jp) {
    // 전달된 파라미터 출력
    // : 전달된 인수들(파라미터 이름과 값)을 전부 하나씩 출력
    // ex) 파라미터명 : no, 값 : 1
        log.info("printParam()");
        Signature signature = jp.getSignature();
        // 타겟 메서드의 파라미터 이름 가져오기
        String[] parameterNames = ((MethodSignature) signature).getParameterNames();
        // 타겟 메서드의 파라미터 값 가져오기
        // ex) ["id", "title", "content"]
        Object[] args = jp.getArgs();
        // 파라미터 이름과 값 출력
        // 파라미터 값 목록
        // : 둘을 같은 인덱스끼리 매칭해 출력
        // ex) 파라미터명 : id, 값 : 5
        if( parameterNames != null ) {
            for (int i = 0; i < parameterNames.length; i++) {
                String paramName = parameterNames[i];
                Object paramValue = args[i];
                log.info("파라미터명 : {}, 값 : {}", paramName, paramValue);
            }
        }
    }

    /**
     * @Around 조인포인트를 적용하면, @After 어드바이스는 실행되지 않는다.
     * (Around 에서 직접 after 를 호출하여 실행시킨다.)
     * 
     * ProceedingJoinPoint : 어드바이스에서 타겟 메서드의 실행을 제어하고 호출하는 객체
     * - proceed()         : 타겟 메서드 호출 (이전과 다르게 타겟 직접 제어)
     * @param jp
     * @return
     */
    @Around("execution(* com.aloha.spring_aop.service.BoardService*.*(..))")
    // 메서드 실행 전체를 감싸서 제어
    // 1. 메서드 실행 전 로그 찍기
    // 2. 반드시 jp.proceed() 호출해야 실제 메서드 실행
    // 3. 메서드 후 로그 찍기 (여기서는 after() 직접 호출)
    // 4. 예외 감지 및 반환 값 처리 가능
    // 즉, 알바생이 일하는 전체 과정을 처음부터 끝까지 매니저가 직접 감시하고 기록
    //      가장 강력한 AOP 기능
    public Object around(ProceedingJoinPoint jp) {
        // jp.getSignature() : 타겟 메서드 시그니처 정보(반환 타입, 패키지.클래스.메서드) 반환
        // jp.getArgs()      : 타겟 메서드의 매개변수를 반환
        log.info("===============================================");
        log.info("[@Around] #####################################");
        log.info("target : " + jp.getTarget().toString());
        log.info("signature : " + jp.getSignature());
        log.info("args : " + Arrays.toString(jp.getArgs()));
        LocalDateTime time = LocalDateTime.now();
        log.info("현재 시간 : " + time);

        Object result = null;
        try {
            result = jp.proceed();  // 타겟 메서드 호출
            // 타겟 메서드 직접 호출
            // : 호출하지 않으면 실제 메서드는 실행X
            if (result != null) 
                log.info("반환값 : " + result.toString());
        } catch (Throwable e) {
            log.error("예외가 발생하였습니다.");
            e.printStackTrace();
        }
        after(jp);                  // @After 어드바이스 직접 호출
        // @Around 사용 시 @After는 자동 실행X
        log.info("===============================================");
        return result;
    } 
    /**
     * 전체 흐름 요약
     * 1. @Before : 메서드 시작하기 직전 실행 (메서드 실행 전)
     * 2. @After : 끝나기만 하면 무조건 실행 (메서드 실행 후)
     * 3. @AfterReturning : return 되어 정상 끝남 (정상 종료 시)
     * 4. @AfterThrowing : 메서드 중간에 터짐 (예외 발생 시)
     * 5. @Around : 전/중/후 모두 조정 가능 (전체 감싸기)
     */
    
}
