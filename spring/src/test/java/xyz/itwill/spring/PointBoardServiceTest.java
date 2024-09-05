package xyz.itwill.spring;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import xyz.itwill09.dto.PointBoard;
import xyz.itwill09.dto.PointUser;
import xyz.itwill09.service.PointBoardService;

//SpringMVC 기능을 사용한 웹프로그램에서 TransactionManager를 사용해 트렌젝션을 관리하는 방법
//1.spring-tx 라이브러리를 프로젝트에 빌드 처리 - 메이븐 : pom.xml
// => spring-jdbc 라이브러리를 프로젝트에 빌드 처리하면 라이브러리 의존관계에 의해 자동으로 빌드 처리
//2.Spring Bean Configuration File(root-context.xml)에 TransactionManager 관련 클래스를 Spring Bean으로 등록
//3.Spring Bean Configuration File(servlet-context.xml)에 트렌젝션 처리를 위한 Spring AOP 설정

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class PointBoardServiceTest {
	@Autowired
	private PointBoardService pointBoardService;
	
	@Test
	public void test1() {
		//PointBoard board=PointBoard.builder().writer("abc123").content("테스트-1").build();
		PointBoard board=PointBoard.builder().writer("xyz789").content("테스트-2").build();
		
		//PointBoardService 객체로 addPointBoard() 매소드를 호출하여 POINT_BOARD 테이블에 행 삽입
		// => POINT_USER 테이블에서 게시글의 작성자에 대한 행의 POINT 컬럼값을 증가되도록 변경
		// => POINT_USER 테이블에서 게시글의 작성자에 대한 행을 검색하여 회원정보로 반환
		// => 게시글의 작성자에 대한 회원정보가 POINT_USER 테이블에 행으로 저장되어 있지 않은 경우 예외 발생
		//문제점 : 예외 발생전에 게시글 삽입에 대한 SQL 명령은 이미 실행되어 POINT_BAORD 테이블에는
		//비정상적인 행 삽입 처리
		//해결법 : 예외가 발생되기 전에 실행된 모든 SQL 명령(INSERT, UPDATE, DELETE)은 롤백 처리되도록 설정
		// => Spring 프레임워크에서는 TransactionManager을 사용해 일관성 있는 트렌젝션 관리 기능 제공 
		PointUser user=pointBoardService.addPointBoard(board);
		log.info(user.toString());
		log.info(pointBoardService.getPointBoardList().toString());
	}
	
	/*
	@Test
	public void test2() {
		//PointBoardService 객체로 removePointBoard() 매소드를 호출하여 POINT_BOARD 테이블에 저장된 행 삭제
		// => POINT_USER 테이블에서 게시글의 작성자에 대한 행의 POINT 컬럼값을 감소되도록 변경
		// => POINT_USER 테이블에서 게시글의 작성자에 대한 행을 검색하여 회원정보로 반환
		// => 게시글이 없거나 게시글의 작성자에 대한 회원정보가 POINT_USER 테이블에 행으로 
		//저장되어 있지 않은 경우 예외 발생 
		PointUser user=pointBoardService.removePointBoard(1);
		log.info(user.toString());
		log.info(pointBoardService.getPointBoardList().toString());
	}
	*/
}














