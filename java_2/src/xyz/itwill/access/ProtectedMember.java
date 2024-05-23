package xyz.itwill.access;

//protected : 필드, 생성자, 메소드의 접근을 제한하기 위한 접근 제한자
// = > 필드, 생성자, 메소드를 같은 패키지의 클래스엣 사용가느아도록 접근 제한자
// = > 필드, 생성자, 메소드를 다른 패키지의 클래스에서 사용할 경우 에러가 발생
// 상속을 할 경우 사용 가능
public class ProtectedMember {
		protected int num;
		
		protected void display() {
			System.out.println("num = "+num);
		}
}
