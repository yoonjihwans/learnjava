package xyz.itwill04.bean;

import org.springframework.stereotype.Component;

//@Component : 클래스를 스프링 컨테이너가 관리하는 Spring Bean로 등록하기 위한 어노테이션
// => 클래스의 이름을 식별자(beanName)로 사용 - 첫번째 문자는 소문자로 변환되어 사용
// => @Component 어노테이션의 value 속성을 사용하여 식별자 변경 가능 - value 속성외에 다른
//속성이 없는 경우 속성값만 설정 가능
@Component("bean")
public class ComponentAnnotationBean {
	public ComponentAnnotationBean() {
		System.out.println("### ComponentAnnotationBeanApp 클래스의 기본 생성자 호출 ###");
	}
	
	public void display() {
		System.out.println("*** ComponentAnnotationBeanApp 클래스의 display() 메소드 호출 ***");
	}
}