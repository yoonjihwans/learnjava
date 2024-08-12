package xyz.itwill.dto;

//MYCOMMENT 테이블과 MYUSER 테이블에 저장된 행을 1:1 관계로 결합하여 검색된 결과값을 저장하기
//위한 클래스 - 검색행의 컬럼값이 저장된 객체를 필드에 저장
// => 기존 클래스를 재사용하여 포함관계의 클래스로 필드 작성 - 생산성 증가 및 유지보수의 효율성 증가
public class MyCommentUser2 {
	//MYCOMMENT 테이블의 검색행(게시글정보) 하나를 객체로 제공받아 저장하기 위한 필드
	private MyComment1 comment;
	
	//MYUSER 테이블의 검색행(회원정보) 하나를 객체로 제공받아 저장하기 위한 필드
	private MyUser user;
	
	public MyCommentUser2() {
		// TODO Auto-generated constructor stub
	}

	public MyComment1 getComment() {
		return comment;
	}

	public void setComment(MyComment1 comment) {
		this.comment = comment;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}
}