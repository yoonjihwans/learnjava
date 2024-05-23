package xyz.itwill.realization;

//클래스는 extends 키워드로 상속받아 사용 - 단일 상속
//=> 자시클래스는 부모클래스의 필드 또는 메소드를 상속받아 사용
// 인터페이스는 implements 키워드로 상속받아 사용 - 다중상속
//=> 자식클래스는 부모인터페이스의 모든 추상메소드를 오버라이딩 선언하며 사용
public class WolfHuman extends Human implements Wolf {

	@Override
	public void fastWlak() {
		System.out.println("[늑대] 네발로 빠르게 달리는 능력");
		
	}

	@Override
	public void cryLoudly() {
		System.out.println("[늑대] 큰소리로 울부짖을 수 능력");
		
	}
	
	public void change() {
		System.out.println("[늑대인간]변신할 수 능력");
	}

}
