package xyz.itwill.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.itwill.auth.CustomUserDetails;

//Spring Security : 인증과 인가 기능을 제공하는 보안 프레임워크
//인증(Authentication) : 프로그램을 사용할 수 있는 사용자가 맞는지를 확인하는 절차
// => 인증을 정상적으로 수행하기 위해서는 사용자를 구분할 수 있는 정보 필요 - Credential
//인가(Authorization) : 인증된 사용자가 요청한 자원에 접근 가능한가를 결정하는 절차
// => 인증 처리 후 권한을 부여 받을 수 있으며 권한은 일반적으로 역활(Role) 형태로 부여

//Spring Security는 인증과 인가를 위해 Principal 객체를 아이디로 사용하고 Credential 객체를
//비밀번호로 사용하는 Credential 기반의 인증 방식을 사용

//Spring Security를 SpringMVC 프로그램에 적용하여 제공 받을 수 있는 기능 - 필터 사용
// => 다양한 형태(폼로그인 인증, 토큰 기반 인증, OAuth2 기반 인증, LDAP 인증)의 사용자 인증 방법 제공
// => 프로그램 사용자의 역활(Role)에 따른 권한 레벨 적용
// => 프로그램에서 제공하는 자원에 대한 접근 제어
// => 데이타 암호화
// => SSL 적용
// => 일반적으로 알려진 웹보안 공격 차단

//Spring Security를 SpringMVC 프로그램에 적용하는 방법
//1.spring-security-web, spring-security-core, spring-security-config, spring-security-tablibs
//라이브러리를 프로트젝에 빌드 처리 - 메이븐 : pom.xml
//2.[web.xml] 파일에 Spring Security 기능을 제공하는 필터 클래스를 필터로 등록하고 필터가 실행
//되기 위한 URL 패턴을 매핑 처리
//3.[web.xml] 파일에 Spring Security 기능의 필터가 사용하기 위한 정보를 제공하는 Spring Bean
//Configuration File 설정 - ContextLoaderListener 클래스가 읽을 수 있도록 파일 경로 지정
//4.Spring Security 기능을 구현하기 위한 Spring Bean Configuration File을 생성해 환경 설정
// => Spring Security 관련 필터가 동작되기 위한 정보를 security 네임스페이스를 추가하여
//spring-security.xsd 파일에 설정된 엘리먼트를 사용해 작성

//Spring Security Filter의 종류
//1.SecurityContextPersistenceFilter : SecurityContextRepository에서 SecurityContext를 가져오거나 생성하는 필터
//2.LogoutFilter : 로그아웃 요청을 처리하는 필터
//3.UsernamePasswordAuthenticationFilter : 아이디와 비밀번호를 사용하는 Form 기반 유저 인증을 처리하는 필터
// => Authentication 객체를 만들고 AuthenticationManager에게 인증 처리 위임
// => AuthenticationManager는 실질적인 인증에 대한 검증 단계를 총괄하는 AuthenticationProvider에게
//인증 처리를 위임 - UserDetailService와 같은 서비스를 사용해서 인증 처리
//4.ConcurrentSessionFilter : 동시 세션과 관련된 필터 - 이중 로그인 방지
//5.RememberMeAuthenticationFilter : 세션이 사라지거나 만료 되더라도 쿠키 또는 DB를 사용하여
//저장된 토큰을 기반으로 인증 처리하는 필터
//6.AnonymousAuthenticationFilter : 사용자 정보가 인증되지 않았다면 익명 사용자 토큰을 반환하는 필터
//7.SessionManagementFilter : 로그인 후 Session과 관련된 작업을 처리하는 필터
//8.ExceptionTranslationFilter : 필터 체인 내에서 발생되는 인증 및 인가 관련 예외를 처리하는 필터
//9.FilterSecurityInterceptor : 권한 부여와 관련한 결정을 AccessDecisionManager에게 위임해 권한
//부여 결정 및 접근 제어를 처리하는 필터
//10.HeaderWriterFilter: Request의 HTTP 헤더를 검사해 Header를 추가하거나 빼주는 필터
//11.CorsFilter : 허가된 사이트나 클라이언트의 요청인지 검사하는 필터
//12.CsrfFilter : CSRF Tocken을 사용하여 CSRF 공격을 막아주는 기능을 제공하는 필터

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	/*
	private final SecurityUserService securityUserService;
	
	//요청 처리 메소드의 매개변수를 Principal 인터페이스로 작성하면 Front Controller에게
	//Principal 객체를 제공받아 사용 가능
	// => Principal 객체 : 로그인 사용자 정보가 저장된 객체 - 로그인 사용자의 아이디 제공
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Principal principal) {
		if(principal != null) {//인증 처리된 사용자인 경우
			//Principal.getName() : 인증 처리된 사용자의 식별자(아이디)를 반환하는 메소드
			//log.warn("아이디 = "+principal.getName());
			
			SecurityUser user=securityUserService.getSecurityUserByUserid(principal.getName());
			log.warn("아이디 = "+user.getUserid());
			log.warn("이름 = "+user.getName());
			log.warn("이메일 = "+user.getEmail());
		}
		return "main_page";
	}
	*/

	//Principal 인터페이스 >> Authentication 인터페이스 >> AbstractAuthenticationToken 추상클래스
	// >> UsernamePasswordAuthenticationToken 클래스 - 구현 클래스
	
	//요청 처리 메소드의 매개변수를 Authentication 인터페이스로 작성하면 Front Controller에게
	//Authentication 객체를 제공받아 사용 가능
	// => Authentication 객체 : 로그인 사용자 및 권한 정보가 저장된 객체
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Authentication authentication) {
		if(authentication != null) {//인증 처리된 사용자인 경우
			//Authentication.getPrincipal() : 로그인 사용자 및 권한 정보가 저장된 UserDetailes
			//객체(CustomUserDetailes 객체)를 반환하는 메소드
			// => Object 객체를 반환하므로 명시적 객체 형변환 후 사용
			CustomUserDetails user=(CustomUserDetails)authentication.getPrincipal();
			
			log.warn("아이디 = "+user.getUserid());
			log.warn("이름 = "+user.getName());
			log.warn("이메일 = "+user.getEmail());
		}
		return "main_page";
	}
	
	@RequestMapping(value = "/guest/", method = RequestMethod.GET)
	public String guestPage() {
		return "guest_page";
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public String userPage() {
		return "user_page";
	}
	
	@RequestMapping(value = "/manager/", method = RequestMethod.GET)
	public String managerPage() {
		return "manager_page";
	}
	
	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public String adminPage() {
		return "admin_page";
	}
}
