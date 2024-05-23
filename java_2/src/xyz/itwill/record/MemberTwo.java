package xyz.itwill.record;

//레코드(Record) :  JDK14에서 만들어진 새로운 JAVA 자료형
//= > 클래스보다 간결하게 값을 저장할 목적의 객체를 생성하기 위한 자료형
// = > 필드값을 변경할 수 없는 객체를 생성하기 위한 목적으로 선언 - 레코드는  final 필드만 작성 가능
 
//회원정보(아이디 이메일 이름)를 저장하기 위한 레코드
// = > 레코드 선언시 소괄호 안에 필드를 작성하면 생성자 및 메소드가 자동 생성
// = > 필드명 동일한 이름으로 필드값을 반환하는 메소드
public record MemberTwo(String id, String name, String email) {

}
