package xyz.itwill05.lombok;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@AllArgsConstructor : 모든 필드에 대한 초기화 작업을 실행할 수 있는 매개변수가 작성된 생성자 제공하는 어노테이션
//@RequiredArgsConstructor : final 제한자를 사용한 필드에 대한 초기화 작업을 실행할 수 있는 
//매개변수가 작성된 생성자 제공하는 어노테이션 
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	//필드에 @Autowired 어노테이션을 사용하여 스프링 컨테이너에게 클래스(Spring Bean)의 객체를
	//제공받아 필드에 저장되도록 의존성 주입 - 필드 레벨의 의존성 주입
	// => 필드의 Setter 메소드가 없어도 Setter Injection을 사용해 의존성 주입
	// => 가장 보편적인 의존성 주입 방법이지만 순환 참조시 에러가 발생되지 않아 StackOverflow 발생 가능
	//@Autowired
	//private MemberDAO memberDAO;
	
	//@RequiredArgsConstructor 어노테이션을 사용해 생성자로 필드를 초기화 처리하기 위해서는
	//필드에 final 제한자를 사용하여 작성 - 매개변수가 작성된 생성자로 의존성 주입
	// => final 제한자 대신 필드에 @NonNull 어노테이션 사용하여 작성 가능 
	//private final MemberDAO memberDAO;
	
	//@NonNull : @RequiredArgsConstructor 어노테이션을 사용해 초기화 처리하기 위한 필드를 
	//설정하기 위한 어노테이션
	@NonNull
	private MemberDAO memberDAO;
	
	/*
	public MemberServiceImpl() {
		log.info("### MemberServiceImpl 클래스의 기본 생성자 호출 ###");
	}
	*/

	/*
	//매개변수가 작성된 생성자에 @Autowired 어노테이션을 사용하여 스프링 컨테이너에게 클래스
	//(Spring Bean)의 객체를 제공받아 필드에 저장되도록 의존성 주입 - Constructor 레벨의 의존성 주입
	// => 순환 참조 방지를 위해 생성자를 사용한 의존성 주입 - 순환 참조 발생시 에러 발생
	// => 매개변수가 작성된 생성자가 하나만 존재할 경우 @Autowired 어노테이션 생략 가능
	//@Autowired
	public MemberServiceImpl(MemberDAO memberDAO) {
		log.info("### MemberServiceImpl 클래스의 매개변수가 작성된 생성자 호출 ###");
		this.memberDAO=memberDAO;		
	}
	*/
	
	/*
	//필드에 대한 Setter 메소드에 @Autowired 어노테이션을 사용하여 스프링 컨테이너에게 클래스
	//(Spring Bean)의 객체를 제공받아 필드에 저장되도록 의존성 주입 - Setter 레벨의 의존성 주입
	// => Setter 메소드의 접근 제한자가 [public]인 경우 Setter 메소드를 호출하여 의존관계 변경 가능
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	*/

	@Override
	public void addMember(Member member) {
		log.info("*** MemberDAOImpl 클래스의 addMember(Member member) 메소드 호출 ***");
		memberDAO.insertMember(member);
	}

	@Override
	public void modifyMember(Member member) {
		log.info("*** MemberDAOImpl 클래스의 modifyMember(Member member) 메소드 호출 ***");
		memberDAO.updateMember(member);
	}

	@Override
	public void removeMember(String id) {
		log.info("*** MemberDAOImpl 클래스의 removeMember(String id) 메소드 호출 ***");
		memberDAO.deleteMember(id);
	}

	@Override
	public Member getMember(String id) {
		log.info("*** MemberDAOImpl 클래스의 getMember(String id) 메소드 호출 ***");
		return memberDAO.selectMember(id);
	}

	@Override
	public List<Member> getMemberList() {
		log.info("*** MemberDAOImpl 클래스의 getMemberList() 메소드 호출 ***");
		return memberDAO.selectMemberList();
	}

}