package com.aloha.spring_aop;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 해당 클래스 언제 필요한가?
 * : Spring Boot 프로젝트를 "톰캣 같은 외부 서버(WAR 배포) 로 실행할 때 필요
 *   기본적으로 스프링부트는 jar로 실행(내장 톰캣)
 */
public class ServletInitializer extends SpringBootServletInitializer {
// SpringBootServletInitializer
// : WAR 배포를 가능하게 해주는 스프링부트의 연결 어댑터
// 즉, 내장 톰캣 없이도 실행 가능하게 만들어줌!
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	// WAR 방식으로 실행
	// : 어떤 스프링부트 애플리케이션을 시작할지 설정하는 부분
	// SpringApplicationBuilder
	// : 스프링 애플리케이션을 생성하는 도구
		return application.sources(SpringAopApplication.class);
		// 시작점이 되는 메인 클래스 지정
		// SpringAopApplication.class
		// : 내가 만든 스프링부트 프로젝트의 @SpringBootApplication 선언된 메인 클래스
		// 즉, 톰캣이나 웹로직 같은 WAS 가 이 앱을 실행할 때
		// 		이 클래스를 시작점으로 실행해줘! 라고 알려주는 것
	}

}
/**
 * 정리
 * 1. WAR로 배포할 때 스프링을 부팅해주는 어댑터
 * 2. 외부 WAS에서 스프링부트를 실행할 준비를 해주는 클래스
 * 3. 메인 클래스를 설정해서 WAS가 프로젝트를 어떻게 시작해야할지 알려주는 역할
 */

/**
 * jar 실행과 war 실행 차이
 * 1. jar (내장 톰캣)
 * : java -jar 로 실행 / ServletInitializer 필요 없음
 * -> 카페에서 시켜 먹는 음료 (스스로 제공)
 * 2. war (외부 톰캣, 웹로직 등 WAS 서버)
 * : WAS가 읽어서 실행 / ServletInitializer 필요함
 * -> 손님이 텀플러 가져와서 거기 주입해달라는 것 (외부 용기)
 * 즉, 텀블러 크기(WAS 환경)에 맞춰 음료(스프링 앱)를 담아주는 도구
 * 
 * ## WAS 란?
 * : Web Application Server
 *   웹 애플리케이션을 실행하고 동작시키는데 필요한 환경을 제공하는 소프트웨어
 *   DB 조회 혹은 복잡한 비즈니스 로직 처리하여 사용자 맞춤형 동적 콘텐츠(Dynamic Content) 생성하는 핵심 미들웨어
 *	 JSP, PHP 등 사용하여 그때그때 새로운 데이터 만들어 웹 서버를 통해 클라이언트에게 전달하는 역할
 *	<-> 정적 파일 : HTMl, 이미지
 * 
 */
