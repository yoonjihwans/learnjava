package xyz.itwill.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;

//인증된 사용자 정보와 권한 정보를 저장하기 위한 클래스
// => 인증 성공 후 인증 정보 및 권한 정보가 저장된 UserDetails 객체를 Spring Security로부터 제공  
//받아 사용 가능하지만 사용자 정보 및 권한 정보를 원하는 형태로 제공받기 위해 클래스 작성 
// => UserDetails 인터페이스를 상속받아 작성 - User 클래스를 상속받아 작성 가능
@Data
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	//인증된 사용자 정보가 저장될 필드 작성
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String enabled;
	
	//인증된 사용자의 모든 권한 정보가 저장될 필드 작성
	private List<GrantedAuthority> secuthryAuthList;

	//매개변수로 전달받은 SecurityUser 객체의 필드값을 CustomUserDetails 객체의 필드에 저장
	public CustomUserDetails(SecurityUser user) {
		this.userid=user.getUserid();
		this.passwd=user.getPasswd();
		this.name=user.getName();
		this.email=user.getEmail();
		this.enabled=user.getEnabled();
		
		this.secuthryAuthList=new ArrayList<GrantedAuthority>();
		//검색된 사용자의 권한 정보를 GrantedAuthority 객체로 생성하여 List 객체의 요소값으로 저장
		for(SecurityAuth auth : user.getSecurityAuthList()) {
			secuthryAuthList.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
	}
	
	//인증된 사용자의 권한정보를 반환하는 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return secuthryAuthList;
	}

	//인증된 사용자의 비밀번호를 반환하는 메소드
	@Override
	public String getPassword() {
		return passwd;
	}

	//인증된 사용자의 식별자(아이디)를 반환하는 메소드
	@Override
	public String getUsername() {
		return userid;
	}

	//인증된 사용자의 유효기간 상태를 반환하는 메소드
	// => false : 사용자 유효기간 초과 상태, true : 사용자 유효기간 미초과 상태
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//인증된 사용자의 잠금 상태를 반환하는 메소드
	// => false : 잠금 상태, true : 해제 상태
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//인증된 사용자 비밀번호의 유효기간 상태를 반환하는 메소드
	// => false : 사용자 비밀번호 유효기간 초과 상태, true : 사용자 비밀번호 유효기간 미초과 상태	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//인증된 사용자의 활성화 상태를 반환하는 메소드
	// => false : 사용자 비활성화 상태, true : 사용자 비활성화 상태  
	@Override
	public boolean isEnabled() {
		if(enabled.equals("0")) {
			return false; 
		} else {
			return true; 
		}
	}
}
