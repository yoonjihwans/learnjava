package xyz.itwill09.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dto.Student;
import xyz.itwill09.mapper.StudentMapper;

//DAO 클래스(Repository 클래스) : 저장매체에 행을 삽입하고 저장된 행을 변경, 삭제, 검색하는
//기능을 제공하기 위한 클래스
// => DAO 클래스가 교체돼도 의존관계로 설정된 Service 클래스에 영향을 최소화 하기 위해
//인터페이스를 상속받아 작성하는 것을 권장 - 유지보수의 효율성 증가

//DBMS 서버를 저장매체로 사용하여 SQL 명령을 전달하여 행의 삽입, 변경, 삭제, 검색 처리
// => DAO 클래스의 메소드는 DBMS 서버에 접속해 하나의 SQL 명령을 전달하여 실행하고 실행결과를
//Java 객체(값)으로 반환되도록 작성 - JDBC

//SpringMVC 기능으로 웹프로그램 작성시 Mybatis 프레임워크를 사용해 DAO 클래스를 작성하는 방법
//1.DataSource 관련 라이브러리와 Mybatis 관련 라이브러리를 프로젝트에 빌드 처리 - 메이븐 : pom.xml
// => ojdbc, spring-jdbc(spring-tx), mybatis, mybatis-spring
//2.Mybatis 프레임워크의 환경설정파일(mybatis-config.xml - settings 엘리먼트) 작성
// => [src/main/webapp] 폴더에 환경설정파일을 작성해야 스프링 컨테이너(WebApplicationContext 객체)가
//환경설정파일을 제공받아 SqlSessionFactory 객체 생성 가능
//3.Spring Bean Configuration File(root-context.xml)에 DataSource 관련 클래스, SqlSessionFactory 
//관련 클래스, SqlSession 관련 클래스, TransactionManager 관련 클래스를 Spring Bean으로 등록
//4.매퍼 파일을 이용해 DAO 클래스 작성

//DAO 클래스는 Service 클래스에서 객체로 제공받아 사용할 수 있도록 Spring Bean으로 등록
// => DAO 클래스는 @Repository 어노테이션을 사용하여 Spring Bean으로 등록 처리
// => @Repository 어노테이션을 스프링 컨테이너가 처리하기 위해서는 반드시 클래스가 작성된 패키지를 
//Spring Bean Configuration File(servlet-context.xml)의 component-scan 엘리먼트로 검색되도록 설정
@Repository
//final 제한자를 사용한 필드만 초기화 처리하는 생성자를 만들어 주는 어노테이션
@RequiredArgsConstructor
public class StudentDAOImpl implements StudentDAO {
	//DAO 클래스의 메소드에서는 매퍼에 등록된 SQL 명령을 제공받아 DBMS 서버에 전달하여 실행하고
	//실행결과를 Java 객체로 반환받기 위해 SqlSession 객체 필요
	// => SqlSession 객체가 저장될 수 있는 필드를 작성해 스프링 컨테이너로부터 객체를 제공받아
	//필드에 저장되도록 의존성 주입
	
	//필드에 @Autowired 어노테이션을 사용해 객체가 저장되도록 의존성 주입 
	// => 필드 레벨의 의존성 주입
	//@Autowired
	//private SqlSession sqlSession;

	//@Autowired 어노테이션을 사용한 생성자로 필드에 객체가 저장되도록 의존성 주입 
	// => 생성자 레벨의 의존성 주입 : 순환참조 방지
	// => 생성자가 하나만 작성된 경우 @Autowired 어노테이션 생략 가능
	private final SqlSession sqlSession;

	@Override
	public int insertStudent(Student student) {
		return sqlSession.getMapper(StudentMapper.class).insertStudent(student);
	}

	@Override
	public List<Student> selectStudentList() {
		return sqlSession.getMapper(StudentMapper.class).selectStudentList();
	}
}