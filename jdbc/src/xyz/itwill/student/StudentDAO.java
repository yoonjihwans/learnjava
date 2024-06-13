package xyz.itwill.student;

import java.util.List;

//DAO 클래스가 상속받기 위한 인터페이스
// => 인터페이스에 추상메소드를 작성하여 인터페이스를 상속받은 모든 자식클래스(DAO 클래스)에
//동일한 메소드를 작성하도록 메소드 작성 규칙 제공
// => 프로그램에서 사용하는 DAO 클래스가 변경돼도 프로그램에 영향을 최소화 하기 위한 인터페이스 선언
public interface StudentDAO {
	//매개변수로 학생정보(StudentDTO 객체)를 전달받아 STUDENT 테이블에 행으로 삽입하여 저장하고
	//삽입행의 갯수를 반환하는 메소드
	// => 학생정보를 매개변수로 하나씩 전달받아 처리 - 비권장
	//int insertStudent(int no, String name, String phone, String address, String birthday);
	// => 학생정보를 매개변수로 한번에 전달받아 처리 - 권장
	int insertStudent(StudentDTO student);

	//매개변수로 학생정보(StudentDTO 객체)를 전달받아 STUDENT 테이블에 저장된 행을 변경하고
	//변경행의 갯수를 반환하는 메소드
	int updateStudent(StudentDTO student);

	//매개변수로 학번(정수값)를 전달받아 STUDENT 테이블에 저장된 행을 삭제하고 삭제행의 갯수를
	//반환하는 메소드
	int deleteStudent(int no);
	
	//매개변수로 학번(정수값)를 전달받아 STUDENT 테이블에 저장된 하나의 행(학생정보)을 검색하고 
	//검색된 행(학생정보)을 StudentDTO 객체로 바꾸어 반환하는 메소드
	StudentDTO selectStudentByNo(int no);
	
	//매개변수로 이름(문자열)를 전달받아 STUDENT 테이블에 저장된 다수의 행(학생정보)을 검색하고 
	//검색된 행(학생정보)을 StudentDTO 객체로 바꾸어 List 객체에 요소로 추가하여 반환하는 메소드
	List<StudentDTO> selectStudentByName(String name);

	//STUDENT 테이블에 저장된 모든 행(학생정보)을 검색하고 검색된 행(학생정보)을 StudentDTO 
	//객체로 바꾸어 List 객체에 요소로 추가하여 반환하는 메소드
	List<StudentDTO> selectStudentAll();
}