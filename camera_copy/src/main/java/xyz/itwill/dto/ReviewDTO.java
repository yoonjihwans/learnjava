package xyz.itwill.dto;

/*
CREATE TABLE REVIEW(REVIEW_NUM NUMBER PRIMARY KEY, REVIEW_USERS_NO NUMBER CONSTRAINT 
REVIEW_USERS_N0_FK REFERENCES USERS(USERS_NO),REVIEW_TITLE VARCHAR2(500), REBVIEW_CONENT
VARCHAR2(4000), REVIEW_IMAGE VARCHAR2(100), REVIEW_STATUS NUMBER(1), REVIEW_DATE DATE);
SELECT * FROM REVIEW;

CREATE SEQUENCE REVIEW_SEQ;
*/

/*

이름              널?       유형             
--------------- -------- -------------- 
REVIEW_NUM      NOT NULL NUMBER          // 리뷰 글 번호
REVIEW_USERS_NO          NUMBER          // 회원 번호 (fk) 회원 테이블 참조
REVIEW_TITLE             VARCHAR2(500)   // 리뷰 제목
REVIEW_CONENT           VARCHAR2(4000)  // 리뷰 내용
REVIEW_IMAGE             VARCHAR2(100)   // 리뷰 이미지
REVIEW_STATUS            NUMBER(1)       // 리뷰 상태 0:삭제글, 1:일반글 
REVIEW_DATE              DATE            // 리뷰 작성일

  */

public class ReviewDTO {
	private int reviewNum;
	private int reviewUserNo;
	private String usersName;
	private String reviewTitle;
	private String reviewContent;
	private String reviewImage;
	private int reviewStatus;
	private String reviewDate;

	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int reviewNum, int reviewUserNo, String usersName, String reviewTitle, String reviewContent,
			String reviewImage, int reviewStatus, String reviewDate) {
		super();
		this.reviewNum = reviewNum;
		this.reviewUserNo = reviewUserNo;
		this.usersName = usersName;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewImage = reviewImage;
		this.reviewStatus = reviewStatus;
		this.reviewDate = reviewDate;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public int getReviewUserNo() {
		return reviewUserNo;
	}

	public void setReviewUserNo(int reviewUserNo) {
		this.reviewUserNo = reviewUserNo;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewImage() {
		return reviewImage;
	}

	public void setReviewImage(String reviewImage) {
		this.reviewImage = reviewImage;
	}

	public int getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	
}
