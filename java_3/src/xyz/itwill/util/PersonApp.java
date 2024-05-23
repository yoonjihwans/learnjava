package xyz.itwill.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PersonApp {
	//메소드의 반환형 또는 매개변수에 자료형으로 제네릭을 사용한 클래스(인터페이스)를 작성할
	//경우 메소드 반환형 앞에 사용할 제네릭을 생성하여 메소드 작성
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for(T t : list) {
			c.accept(t);
		}
		System.out.println();
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		List<T> result=new ArrayList<T>();
		
		for(T t : list) {
			if(predicate.test(t)) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		forEach(Arrays.asList("홍길동","임꺽정","전우치"), str -> System.out.print(str+" "));
		//List.of(E... element) : 매개변수로 전달받은 0개 이상의 객체가 요소값으로 저장된
		//List 객체를 반환하는 정적메소드
		forEach(List.of("홍길동","임꺽정","전우치"), str -> System.out.print(str+" "));
		System.out.println("==============================================================");
		List<Person> personList=new ArrayList<Person>();
		personList.add(new Person("홍길동", 50));
		personList.add(new Person("임꺽정", 29));
		personList.add(new Person("전우치", 35));
		
		System.out.println(personList);
		System.out.println("==============================================================");
		List<Person> thirtyList=filter(personList, person -> person.getAge() >= 30);
		
		System.out.println(thirtyList);
		System.out.println("==============================================================");
	}
}