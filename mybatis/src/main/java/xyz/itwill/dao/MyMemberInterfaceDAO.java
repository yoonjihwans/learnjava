package xyz.itwill.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xyz.itwill.dto.MyMember;
import xyz.itwill.mapper.MyMemberInterfaceMapper;

public class MyMemberInterfaceDAO {
	private static MyMemberInterfaceDAO _dao;
	
	private MyMemberInterfaceDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new MyMemberInterfaceDAO();
	}
	
	public static MyMemberInterfaceDAO getDAO() {
		return _dao;
	}
	
	private SqlSessionFactory getSqlSessionFactory() {
		String resource="mybatis-config.xml";
		
		InputStream in=null;
		try {
			in=Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		
		return new SqlSessionFactoryBuilder().build(in);
	}

	//회원정보를 전달받아 MYMEMBER 테이블의 행으로 삽입하고 삽입행의 갯수를 반환하는 메소드
	public int insertMyMember(MyMember member) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			//SqlSession.getMapper(Class<T> class) : 매개변수에 Class 객체(메모리에 저장된 
			//인터페이스 매퍼)를 전달받아 Mapper 객체로 생성하여 반환하는 메소드
			// => 매개변수에 [XXX.class] 형식으로 클래스 파일을 작성해 Class 객체를 생성하여 전달
			//Mapper 객체 : 추상메소드에 등록된 SQL 명령이 저장된 메소드를 호출할 수 있는 객체
			// => Mapper 객체로 추상메소드를 호출하면 추상메소드가 오버라이드 선언되어 SQL 명령이 저장된 
			//메소드를 호출해 DBMS 서버에 SQL 명령을 전달하여 실행하고 실행결과를 Java 객체로 반환
			return sqlSession.getMapper(MyMemberInterfaceMapper.class).insertMyMember(member);
		} finally {
			sqlSession.close();
		}
	}
	
	//회원정보를 전달받아 MYMEMBER 테이블에 저장된 행을 변경하고 변경행의 갯수를 반환하는 메소드
	public int updateMyMember(MyMember member) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberInterfaceMapper.class).updateMyMember(member);
		} finally {
			sqlSession.close();
		}
	}

	//아이디를 전달받아 MYMEMBER 테이블에 저장된 행을 삭제하고 삭제행의 갯수를 반환하는 메소드
	public int deleteMyMember(String id) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberInterfaceMapper.class).deleteMyMember(id);
		} finally {
			sqlSession.close();
		}
	}
	
	//아이디를 전달받아 MYMEMBER 테이블에 저장된 행을 검색하여 회원정보를 반환하는 메소드
	public MyMember selectMyMember(String id) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberInterfaceMapper.class).selectMyMember(id);
		} finally {
			sqlSession.close();
		}
	}
	
	//MYMEMBER 테이블에 저장된 모든 행을 검색하여 회원목록을 반환하는 메소드
	public List<MyMember> selectMyMemberList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberInterfaceMapper.class).selectMyMemberList();
		} finally {
			sqlSession.close();
		}
	}
}

