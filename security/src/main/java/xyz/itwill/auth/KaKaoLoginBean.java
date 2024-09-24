package xyz.itwill.auth;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

//Kakao OpenAPI를 요청하기 위한 클래스
@Component
public class KaKaoLoginBean {
	private final static String KAKAO_CLIENT_ID="9f127646d59dd6ac2e797e6b4083fa7b";
	private final static String KAKAO_CLIENT_SECRET="WbBVtdcJnUhpJKnTPFnk9gkwZNyGJKnZ";
	private final static String KAKAO_REDIRECT_URI="http://localhost:8000/kakao/callback";
	private final static String SESSION_STATE="kakao_oauth_state";
	//카카오 간편 로그인 후 사용자의 프로필 정보를 조회하기 위한 OpenAPI의 URL 주소
	private final static String PROFILE_API_URL="https://kapi.kakao.com/v2/user/me";
	
	/* 세션 유효성 검증을 위한 난수값을 생성하여 반환하는 메소드 */
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	/* 세션(HttpSession 객체)에 난수값을 속성값으로 저장하는 메소드 */
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}
	
	/* 세션(HttpSession 객체)에 속성값으로 저장된 난수값을 반환하는 메소드 */
	private String getSession(HttpSession session) {
		return (String)session.getAttribute(SESSION_STATE);
	}
	
	/* 인증 처리하는 URL 주소를 생성하기 위한 메소드 */
	public String getAuthorizationUrl(HttpSession session) {
		/* 세션 유효성 검증을 위한 난수값을 생성하여 저장 */
		String state=generateRandomString();
		
		//저장된 난수값을 Session Scope 속성값으로 저정
		setSession(session, state);
		
		//ServiceBuilder 객체를 사용해 OAuth20Service 객체 생성 
		// => OAuth20Service 객체 : OAuth2.0 기반의 인증 방식을 제공하기 위한 객체
		OAuth20Service oauth20Service=new ServiceBuilder()
				.apiKey(KAKAO_CLIENT_ID)
				.apiSecret(KAKAO_CLIENT_SECRET)
				.callback(KAKAO_REDIRECT_URI)
				.state(state)
				.build(KakaoLoginApi.instance());
		
		//OAuth20Service 객체를 사용해 인증 처리 URL 주소를 생성하여 반환
		return oauth20Service.getAuthorizationUrl();
	}
	
	/* 접근토큰(AccessToken)을 발급받아 반환하는 메소드 */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) 
		throws IOException {
		//세션 검증용 난수값을 반환받아 저장
		String sessionState=getSession(session);
		if(StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauth20Service=new ServiceBuilder()
					.apiKey(KAKAO_CLIENT_ID)
					.apiSecret(KAKAO_CLIENT_SECRET)
					.callback(KAKAO_REDIRECT_URI)
					.state(state)
					.build(KakaoLoginApi.instance());
			OAuth2AccessToken accessToken = oauth20Service.getAccessToken(code);
			return accessToken;
		}
		return null;
	}
	
	/* 접근토큰을 사용해 사용자 프로필을 제공 받을 수 있는 OpenAPI 요청해 사용자 프로필을
	JSON 형식의 문자값으로 반환하는 메소드 */
	public String getUserProfile(OAuth2AccessToken oAuth2AccessToken) throws IOException {
		OAuth20Service oauth20Service=new ServiceBuilder()
				.apiKey(KAKAO_CLIENT_ID)
				.apiSecret(KAKAO_CLIENT_SECRET)
				.build(KakaoLoginApi.instance());
		
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauth20Service);
		oauth20Service.signRequest(oAuth2AccessToken, request);
		Response response = request.send();
		
		return response.getBody();
	}
}