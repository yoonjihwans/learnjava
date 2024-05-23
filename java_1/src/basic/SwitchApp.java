package basic;

//switch : 값을 비교하여 명령을 선택 실행하기 위한 선택문을 작성하는 키워드
//형식) switch({변수|연산식}) {
//			case 값1 : 명령; 명령; ... [break;]
//			case 값2 : 명령; 명령; ... [break;]
//			case 값3 : 명령; 명령; ... [break;]
//			...
//			[default : 명령; 명령; ...]
//      }
// => switch 키워드로 제공되는 변수값 또는 연산 결과값을 case 키워드로 제공되는 값과 차례대로 
//비교하여 같은 값의 case 위치에 명령부터 하단에 작성된 모든 명령 실행
// => switch 키워드 및 case 키워드로 제공되는 값으로 실수값 사용 불가능
// => case 키워드로 제공되는 값은 반드시 리터럴 또는 상수만 작성 가능하며 중복 작성될 경우 에러 발생
// => 명령 실행시 break 키워드를 실행하면 switch 명령 종료
// => switch 키워드로 제공되는 값이 case 키워드로 제공되는 값과 모두 다른 경우 default 키워드의 명령 실행
public class SwitchApp {
	public static void main(String[] args) {
		//int choice=1;
		//int choice=2;
		int choice=3;
		
		switch (choice) {
		case 1:  
			System.out.println("수성으로 이동합니다.");
		case 2:
			System.out.println("금성으로 이동합니다.");
		case 3:
			System.out.println("화성으로 이동합니다.");
		}
		System.out.println("==============================================================");
		choice=4;
		
		switch (choice) {
		case 1:  
			System.out.println("수성으로 이동합니다.");
		case 2:
			System.out.println("금성으로 이동합니다.");
		case 3:
			System.out.println("화성으로 이동합니다.");
		}
		System.out.println("==============================================================");
		choice=4;
		
		switch (choice) {
		case 1:  
			System.out.println("수성으로 이동합니다.");
		case 2:
			System.out.println("금성으로 이동합니다.");
		case 3:
			System.out.println("화성으로 이동합니다.");
		default:
			System.out.println("지구로 이동합니다.");
		}
		System.out.println("==============================================================");
		choice=1;
		
		switch (choice) {
		case 1:  
			System.out.println("수성으로 이동합니다.");
			break;
		case 2:
			System.out.println("금성으로 이동합니다.");
			break;
		case 3:
			System.out.println("화성으로 이동합니다.");
			break;
		default:
			System.out.println("지구로 이동합니다.");
		}
		System.out.println("==============================================================");
		int jumsu=85;
		//int jumsu=185;
		
		//변수값이 0~100 범위의 유효값인 경우 등급을 구분하여 출력
		// => 변수값이 0~100 범위의 유효값이 아닌 비정상인 값인 경우 에러 메세지 출력
		if(jumsu >= 0 && jumsu <= 100) {
			String grade="";
			
			switch (jumsu / 10) {
			case 10:
			case 9:
				grade="A"; break;
			case 8:
				grade="B"; break;
			case 7:
				grade="C"; break;
			case 6:
				grade="D"; break;
			default:
				grade="F"; break;
			}
			
			System.out.println("[결과]"+jumsu+"점 = "+grade+"학점");
		} else {
			System.out.println("[에러]0~100 범위를 벗어난 비정상적인 점수가 입력 되었습니다.");
		}
		System.out.println("==============================================================");
	}
}









