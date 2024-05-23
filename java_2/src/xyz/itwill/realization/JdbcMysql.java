package xyz.itwill.realization;

//이클립스를 사용하면 클래스를 이용하여 인터페이스 선언 가능
// = > Package Explorer 뷰에서 클래스 선택 >> 오른쪽 버튼 >> 팝업 메뉴 : Refactor
public class JdbcMysql implements Jdbc {
	@Override
	public void insert() {
		System.out.println("[mysql]학생정보를 삽입하는 기능의 메소드");
	}
	
	@Override
	public void update() {
		System.out.println("[mysql]학생정보를 변경하는 기능의 메소드");
	}
	
	@Override
	public void delete() {
		System.out.println("[mysql]학생정보를 삭제하는 기능의 메소드");
	}
	
	@Override
	public void select() {
		System.out.println("[mysql]학생정보를 감색하는 기능의 메소드");
	}

}
