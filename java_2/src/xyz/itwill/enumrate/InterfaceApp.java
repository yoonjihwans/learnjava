package xyz.itwill.enumrate;

//클래스 또는 인터페이스에 상수필드를 작성할경우 문제점
// => 상수필드를 값을 대표하는 이름으로 사용하기 부적절한 경우 발생
// => 상수필드가 작성된 클래스 또는 인터페이스 자료형보다는 상수필드를 사용하기 위한 접근용도로만 이용
//java 언어에서는 클래스 또는 인터페이스를 상수필드를 작성하여 발생될 수 있는 문제점을 해결
//하기 위해 열거형(EnumerateType)이라는 자료형(참조형)을 제공 

public class InterfaceApp {
	public static void main(String[] args) {
         //인터페이스에 작성된 상수필드 출력
		System.out.println("삽입(InterfaceOne.INSERT)" +interfaceOne.INSERT);
		System.out.println("변경(InterfaceOne.UPDATE)" + interfaceOne.UPDATE);
		System.out.println("삭제(InterfaceOne.DELETE)" + interfaceOne.DELETE);
		System.out.println("검색(InterfaceOne.SELECT)" + interfaceOne.SELECT);
		System.out.println("============================================");
		System.out.println("삽입(InterfaceOne.ADD)" +interfaceTwo.ADD);
		System.out.println("변경(InterfaceOne.MODIFY)" + interfaceTwo.MODIFY);
		System.out.println("삭제(InterfaceOne.REMOVE)" + interfaceTwo.REMOVE);
		System.out.println("검색(InterfaceOne.SEARCH)" + interfaceTwo.SEARCH);
		System.out.println("============================================");
		//인터페이스로 변수를 생성하여 상수필드값 저장 불가능
		//interfaceOne choice = interfaceOne.INSERT;//에러발생
		//상수필드의 자료형과 동일한 자료형으로 변수를 생성해야만 상수필드값을 저장 가능
		int choice = interfaceOne.INSERT;
		System.out.println(choice);
		System.out.println("============================================");
		switch(choice) {
//		case interfaceTwo.INSERT: //가능
		case interfaceOne.INSERT:
			System.out.println("학생정보를 삽입합니다.");
			break;
		case interfaceOne.UPDATE:
			System.out.println("학생정보를 변경합니다.");
			break;
		case interfaceOne.DELETE:
			System.out.println("학생정보를 삭제합니다.");
			break;
		case interfaceOne.SELECT:
			System.out.println("학생정보를 검색합니다.");
			break;
		}
	
	}

}
