package xyz.itwill.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//콜렉션 클래스(Collection Class) : 자료구조(Date Structure)를 사용하여 다수의 객체(값)를
//효율적으로 저장하여 관리(추가, 변경, 삭제, 검색)하기 위한 기능을 제공하는 객체 수집 클래스를 
//포괄적으로 표현힌 이름 - 제네릭 사용
// => 다수의 객체(값)을 저장하기 위한 요소(Element)의 모임
// => 콜렉션 클래스는 Collection 인터페이스 또는 Map 인터페이스를 상속받아 작성된 클래스
// => Collection 인터페이스는 Set 인터페이스와 List 인터페이스의 부모 인터페이스 

//Set 인터페이스를 상속받은 콜렉션 클래스 - HashSet 클래스, TreeSet 클래스 등
// => 요소의 순서가 없으며 같은 객체(값)가 중복 저장되지 않는 기능 제공
public class SetApp {
	public static void main(String[] args) {
		// new 연산자로 HashSet 클래스의 기본 생성자를 호출하여 객체 생성
		// => 제네릭을 사용하지 않고 HashSet 객체 생성 - 비권장
		// => HashSet 객체의 요소는 Object 클래스로 선언되어 있어 모든 클래스의 객체를 전달받아
		// HashSet 객체 요소에 저장 가능
		// => HashSet 객체 요소에 저장된 객체를 반환받아 사용할 경우 반드시 명시적 객체 형변환 사용
		// HashSet set=new HashSet();

		// new 연산자로 HashSet 클래스의 기본 생성자를 호출하여 객체 생성
		// => 제네릭에 Java 자료형(클래스)를 전달하여 HashSet 객체 생성 - 권장
		// => HashSet 객체의 요소는 제네릭으로 전달된 클래스로 선언되어 있어 제네릭으로 전달된
		// 클래스의 객체를 전달받아 HashSet 객체 요소에 저장 가능
		// => HashSet 객체 요소에 저장된 객체를 반환받아 사용할 경우 명시적 객체 형변환 불필요
		// HashSet<String> set=new HashSet<String>();

		// 참조변수를 부모클래스 또는 부모인터페이스를 사용하여 생성하는 것을 권장
		// => 부모클래스 또는 부모인터페이스로 생성된 참조변수에는 자식클래스의 생성자로 생성된 객체 저장 가능
		// => 클래스간의 결합도를 낮춰 유지보수의 효율성 증가
		Set<String> set = new HashSet<String>();

		// Set.add(E emelemt) : Set 객체의 요소에 매개변수로 전달받은 객체(값)을 추가하여 저장하는 메소드
		// => 매개변수에는 제네릭 대신 사용된 클래스로 생성된 객체만 전달하여 저장 가능
		// => Set 객체는 요소의 순서가 없으면 요소에 동일한 객체 저장 불가능
		set.add("홍길동");
		// set.add(12.34);//제네릭으로 설정된 클래스가 아닌 다른 클래스의 객체를 전달하면 에러 발생
		set.add("임꺽정");
		set.add("전우치");

		// Set.toString() : Set 객체에 저장된 모든 요소값(객체)을 문자열로 변환하여 반환하는 메소드
		// System.out.println("set.toString() = "+set.toString());
		System.out.println("set = " + set);
		System.out.println("==============================================================");
		// Set 객체의 요소에는 동일한 객체(메모리주소와 필드값)에 중복 저장 미허용
		set.add("홍길동");
		System.out.println("set = " + set);
		System.out.println("==============================================================");
		// Set.size() : Set 객체에서 객체(값)가 저장된 요소의 갯수를 반환하는 메소드
		System.out.println("Set 객체에 저장된 요소의 객체 = " + set.size());
		System.out.println("==============================================================");
		// Set.remove(E Element) : 매개변수로 전달받은 객체와 동일한 요소값(객체)을 Set 객체에서
		// 삭제하는 메소드
		set.remove("임꺽정");
		System.out.println("set = " + set);
		System.out.println("==============================================================");
		// Set.ilterator() : Set 객체에 저장된 요소겂을 반복 처리할 수 있는 Iterator 객체를 반환
		// Iterator 객체 : 콜렉션 클래스의 객체에 저장된 요소값을 반복 처리하기 위한 기능을 제공하는 객체
		// = > 반복지시자 : 내부의 커서(cursor)를 사용하며 콜렉션 클래스의 객체 요소값을 제공받아 반복 처리
		// = > 제네릭에 클래스를 전달하여 Iterator 객체로 반복 처리할 수 있는 객체 설정
		Iterator<String> iterator = set.iterator();

		// Iterator 객체를 사용해서 Set 객체에 저장된 모든 요소값(객체)을 제공받아 일괄 처리
		// Iterator.hasNext() : Iterator 객체로 처리 가능한 요소값의 존재 유무를 논리값으로 반환하는 메소드
		// = > false : 처리될 요소값 미존재, true : 처리될 요소값 존재
		while (iterator.hasNext()) {
			// Iterator.next() : iterator 객체로 처리될 요소값(객체)를 반환하는 메소드
			// = > 제네릭을 사용했으므로 명시적 객체 형변환 불필요
			String str = iterator.next();
			System.out.println(str);

		}
		System.out.println("==============================================================");
		//향상된 for 구문을 사용하여 Set 객체에 저장된 요소값(객체)를 차례대로 제공받아
		//참조변수에 저장해 일괄처리 가능
		// = > 향상된 for 구문을 사용하면 내부적으로 iterator 객체를 사용해 반복 처리
		for(String str : set) {
			System.out.println(str);
		}
		System.out.println("==============================================================");
	}
}
