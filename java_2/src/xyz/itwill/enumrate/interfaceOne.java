package xyz.itwill.enumrate;

public interface interfaceOne {
     //상수필드(Constant Field) : 프로그램에서 리터럴(값) 대신 사용하기 위해 제공되는 의미있는 필드(단어)
	 // => 클래스에 상수필드를 작성할수있지만 인터페이스에 상수필드를 작성하여 사용하는것을 권장한다.
	 int INSERT=1, UPDATE=2, DELETE=3, SELECT=4; //public static final 제한자 생략가능
}
