package xyz.itwill.enumrate;

public enum Compass {
	// 열거형의 상수필드는 기본적으로 정수형(int)로 설정되며 첨자가 초기값으로 자동 저장
	// 매개변수가 없는 생성자를 사용하여 상수필드 생성
//	EAST, WEST, SOUTH, NORTH;

	// 매개변수가 있는 생성자를 사용하여 상수필드 생성
	EAST("East"), WEST("West"), SOUTH("South"), NORTH("North");

	// 상수필드에 저장된 값을 표현하기 위한 필드 - final 제한자를 사용하는 것을
	// => 필드에 final 제한자로 필드를 작성하면  반드시 매개변수가 있는 생성자를 이용하여 필드 초기화 처리

	private final String value;

	// 필드에 매개변수로 전달받은 값으로 필드를 초기화 처리하기 위한 생성자 - 상수필드를 생성하기 위한
	// = > 상수필드를 생성하기 위한 생성자 이므로 접근제한자를 [private]으로 설정
	private Compass(String value) {
		this.value = value;
	}

	// = > Getter 메소드를 작성하여 필드값을 반환받아 사용 가능
	public String getValue() {
		return value;
	}
}
