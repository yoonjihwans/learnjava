package xyz.itwill.enumrate;

// 열거형(Enumerate) : 상수필드만을 작성하기 위한 자료형 

//형식) public enum 열거형명 { 상수명; 상수명, ...}
// 열거형의 이름은 파스칼 표기법을 사용하여 작성하는 것을 권장

public enum EnumerateOne {
	// 상수필드 작성 - public static final 제한자 및 int 자료형 생략
	// 상수필드에는 자동으로 첨자(intdex - 0부터 1씩 증가되는 정수값)가 기본값으로 저장
	INSERT, UPDATE, DELETE, SELECT;
}
