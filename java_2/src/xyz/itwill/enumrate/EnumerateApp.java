package xyz.itwill.enumrate;

public class EnumerateApp {
		public static void main(String[] args) {
			//열거형에 작성된 상수필드 출력 + 상수필드의 이름 출력
			// = > 열거형에 작성된 상수필드의 이름을 하나의 값으로 사용
			System.out.println("삽입(EnumerateOne.INSERT)" + EnumerateOne.INSERT);
			System.out.println("변경(EnumerateOne.UPDATE)" + EnumerateOne.UPDATE);
			System.out.println("삭제(EnumerateOne.DELETE)" + EnumerateOne.DELETE);
			System.out.println("선택(EnumerateOne.SELECT)" + EnumerateOne.SELECT);
			System.out.println("==============================================");
			System.out.println("삽입(EnumerateTwo.INSERT)" + EnumerateTwo.ADD);
			System.out.println("변경(EnumerateTwo.MODIFY)" + EnumerateTwo.MODIFY);
			System.out.println("삭제(EnumerateTwo.REMOVE)" + EnumerateTwo.REMOVE);
			System.out.println("선택(EnumerateTwo.SERACH)" + EnumerateTwo.SERACH);
			System.out.println("==============================================");
			//열겨형으로 생성된 변수에 해당 열거형에 작성된 상수필드만 저장 가능
			// => 상수필드가 작성된 열겨형을 하나의 자료형으로 사용 가능
			@SuppressWarnings("unused")
			EnumerateOne choic = EnumerateOne.INSERT;
			System.out.println(choic);
			System.out.println("==============================================");
			//나열형으로 선언된 변수값을 자료형(열거형)의 상수필드로만 비교 가능
			// = > case 키워드에는 switch 키워드의 열겨형의 상수필드만 작성하여 비교 가능
			switch(choic){
			case INSERT:
				System.out.println("학생정보를 삽입합니다.");
				break;
			case UPDATE:
				System.out.println("학생정보를 변경합니다.");
				break;
			case DELETE:
				System.out.println("학생정보를 삭제합니다.");
				break;
			case SELECT:
				System.out.println("학생정보를 검색합니다.");
				break;
				
			}
		}
}
