package xyz.itwill.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.RequiredArgsConstructor;
import xyz.itwill.auth.CustomUserDetails;
import xyz.itwill.auth.KaKaoLoginBean;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;
import xyz.itwill.service.SecurityUserService;

//Spring Security는 OAuth2.0을 사용한 인증 처리 기능 제공
// => Google, Kakao, Naver 등의 Social 로그인 기능을 사용한 인증 처리

//카카오의 로그인 OpenAPI를 사용해 간편 로그인 하는 방법
//1.Kakao Developers 페이지 접속 - Kakao 로그인
//2.[내 애플리케이션] >> [애플리케이션 추가하기] >> 앱 아이콘, 앱 이름, 회사명, 카테고리 입력 >> 저장
// => [비즈니스] >> [개인 개발자 비즈 앱 전환] >> 2차 인증 >> 비즈 앱 전환 목적 : [이메일 필수 동의] >> 전환
//3.생성된 애플리케이션 >> [앱키] >> [REST API 키] 복사 
//4.생성된 애플리케이션 >> [플랫폼] >> [Web 플랫폼 등록] >> 사이트 도메인 추가 >> 저장 
//5.생성된 애플리케이션 >> [카카오 로그인] >> [활성화 설정]의 상태를 [ON]으로 변경
//  >> [Redirect URI 등록] >> Redirect URI 주소 입력 >> 저장
// => Redirect URI 주소 : http://localhost:8000/security/kakao/callback
//6.생성된 애플리케이션 >> [카카오 로그인] >> [동의 항목] >> 동의 항목 설정 - 동의된 항목만 응답받아 사용 가능 
//7.생성된 애플리케이션 >> [카카오 로그인] >> [보안] >> Client Secret 코드 발급 및 복사 
// >> 활성화 상태로 변경
//8.scribejava-apis 라이브러리와 json-simple 라이브러리를 프로젝트에 빌드 처리 - 메이븐 : pom.xml
//9.로그인 관련 OpenAPI를 요청하기 위한 KakaoLoginApi 클래스와 KaKaoLoginBean 클래스 작성

@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {
	private final KaKaoLoginBean kaKaoLoginBean;
	private final SecurityUserService securityUserService;
	
	//카카오 로그인 페이지를 요청하기 위한 요청 처리 메소드
	@RequestMapping("/login")
	public String login(HttpSession session) {
		String kakaoAuthUrl=kaKaoLoginBean.getAuthorizationUrl(session);
		return "redirect:"+kakaoAuthUrl;
	}
	
	//카카오 로그인 성공시 요청되는 콜백 URL 주소를 처리하기 위한 요청 처리 메소드
	@RequestMapping("/callback")
	public String login(@RequestParam String code, @RequestParam String state
			, HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken accessToken=kaKaoLoginBean.getAccessToken(session, code, state);
		String apiResult=kaKaoLoginBean.getUserProfile(accessToken);
		//apiResult = {"id":3719292067,"connected_at":"2024-09-25T01:56:26Z","properties":{"nickname":"내삶의선물","profile_image":"http://k.kakaocdn.net/dn/dazM9Q/btsJEGW5mS6/J5HBuuTUjmBfLwAAajWbqk/img_640x640.jpg","thumbnail_image":"http://k.kakaocdn.net/dn/dazM9Q/btsJEGW5mS6/J5HBuuTUjmBfLwAAajWbqk/img_110x110.jpg"},"kakao_account":{"profile_nickname_needs_agreement":false,"profile_image_needs_agreement":false,"profile":{"nickname":"내삶의선물","thumbnail_image_url":"http://k.kakaocdn.net/dn/dazM9Q/btsJEGW5mS6/J5HBuuTUjmBfLwAAajWbqk/img_110x110.jpg","profile_image_url":"http://k.kakaocdn.net/dn/dazM9Q/btsJEGW5mS6/J5HBuuTUjmBfLwAAajWbqk/img_640x640.jpg","is_default_image":false,"is_default_nickname":false},"has_email":true,"email_needs_agreement":false,"is_email_valid":true,"is_email_verified":true,"email":"ocj1778@hanmail.net"}}
		//System.out.println("apiResult = "+apiResult);
		
		//JSON 형식의 문자열을 Java 객체로 변환하여 저장
		JSONParser parser=new JSONParser();
		JSONObject jsonObject=(JSONObject)parser.parse(apiResult);
		Long id=(Long)jsonObject.get("id");
		JSONObject propertiesObject=(JSONObject)jsonObject.get("properties");
		String name=(String)propertiesObject.get("nickname");
		JSONObject kakaoAccountObject=(JSONObject)jsonObject.get("kakao_account");
		String email=(String)kakaoAccountObject.get("email");
		//System.out.println("id = "+id);
		//System.out.println("name = "+name);
		//System.out.println("email = "+email);
		
		//제공받은 프로필 정보를 사용해 사용자 정보 및 권한 정보가 저장된 객체 생성   
		SecurityAuth auth=new SecurityAuth();
		auth.setUserid("kakao_"+id);
		auth.setAuth("ROLE_USER");
		
		List<SecurityAuth> authList=new ArrayList<SecurityAuth>();
		authList.add(auth);
		
		SecurityUser user=new SecurityUser();
		user.setUserid("kakao_"+id);
		user.setPasswd(UUID.randomUUID().toString());
		user.setName(name);
		user.setEmail(email);
		user.setEnabled("1");
		user.setSecurityAuthList(authList);
		
		//카카오 로그인 사용자 정보를 SECURITY_USER 테이블 및 SECURITY_AUTH 테이블 저장
		// => 회원가입과 동일한 기능 구현
		if(securityUserService.getSecurityUserByUserid("kakao_"+id) == null) {
			securityUserService.addSecurityUser(user);
			securityUserService.addSecurityAuth(auth);
		}
		
		//사용자 정보를 제공받아 인증 사용자 정보 및 권한 정보가 저장된 UserDetails 객체 생성 
		CustomUserDetails userDetails=new CustomUserDetails(user);
		
		//UsernamePasswordAuthenticationToken 객체를 생성하여 Spring Security가 사용 가능한
		//인증 사용자로 등록 처리
		// => UsernamePasswordAuthenticationToken : UserDetails 객체를 전달받아 Spring Security가
		//사용 가능한 인증 사용자로 등록하는 기능을 제공하는 객체
		Authentication authentication=new UsernamePasswordAuthenticationToken
				(userDetails, null, userDetails.getAuthorities());
		
		//SecurityContextHolder.getContext() : SecurityContext 객체를 반환하는 정적메소드
		// => SecurityContext 객체 : 인증 처리된 사용자 정보를 세션에 저장하거나 세션에 저장된
		//사용자를 검색하는 기능을 제공하는 객체
		//SecurityContext.setAuthentication(Authentication authentication) : 매개변수로 전달받은
		//Authentication 객체를 세션에 저장하기 위한 메소드
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
}
