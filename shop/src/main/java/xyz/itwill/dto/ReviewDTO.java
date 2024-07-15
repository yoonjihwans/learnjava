package xyz.itwill.dto;

/*
 
*/

/*
이름                   널?       유형             
-------------------- -------- -------------- 
REVIEW_NUM           NOT NULL NUMBER         - 글번호         
REVIEW_MEMBER_NUM             NUMBER         - 작성자 : 로그인 사용자의 회원번호
REVIEW_SUBJECT                VARCHAR2(500)  
REVIEW_CONTENT                VARCHAR2(4000) 
REVIEW_IMAGE                  VARCHAR2(100)  
REVIEW_REGISTER_DATE          DATE           
REVIEW_UPDATE_DATE            DATE           
REVIEW_COUNT                  NUMBER         
REVIEW_REF                    NUMBER         
REVIEW_RESTEP                 NUMBER         
REVIEW_RELEVEL                NUMBER         
REVIEW_STATUS                 NUMBER(1)   
*/

public class ReviewDTO {
	private int reviewNum;
	private int reviewMemberNum;
	private int reviewSubject;
	private int reviewContent;
	private int reviewImage;
	private int reviewResisterDate;
	private int reviewCount;
	private int reviewRef;
	private int reviewRestep;
	private int ReviewRelevel;
	private int ReviewStatus;

	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int reviewNum, int reviewMemberNum, int reviewSubject, int reviewContent, int reviewImage,
			int reviewResisterDate, int reviewCount, int reviewRef, int reviewRestep, int reviewRelevel,
			int reviewStatus) {
		super();
		this.reviewNum = reviewNum;
		this.reviewMemberNum = reviewMemberNum;
		this.reviewSubject = reviewSubject;
		this.reviewContent = reviewContent;
		this.reviewImage = reviewImage;
		this.reviewResisterDate = reviewResisterDate;
		this.reviewCount = reviewCount;
		this.reviewRef = reviewRef;
		this.reviewRestep = reviewRestep;
		ReviewRelevel = reviewRelevel;
		ReviewStatus = reviewStatus;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public int getReviewMemberNum() {
		return reviewMemberNum;
	}

	public void setReviewMemberNum(int reviewMemberNum) {
		this.reviewMemberNum = reviewMemberNum;
	}

	public int getReviewSubject() {
		return reviewSubject;
	}

	public void setReviewSubject(int reviewSubject) {
		this.reviewSubject = reviewSubject;
	}

	public int getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(int reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getReviewImage() {
		return reviewImage;
	}

	public void setReviewImage(int reviewImage) {
		this.reviewImage = reviewImage;
	}

	public int getReviewResisterDate() {
		return reviewResisterDate;
	}

	public void setReviewResisterDate(int reviewResisterDate) {
		this.reviewResisterDate = reviewResisterDate;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getReviewRef() {
		return reviewRef;
	}

	public void setReviewRef(int reviewRef) {
		this.reviewRef = reviewRef;
	}

	public int getReviewRestep() {
		return reviewRestep;
	}

	public void setReviewRestep(int reviewRestep) {
		this.reviewRestep = reviewRestep;
	}

	public int getReviewRelevel() {
		return ReviewRelevel;
	}

	public void setReviewRelevel(int reviewRelevel) {
		ReviewRelevel = reviewRelevel;
	}

	public int getReviewStatus() {
		return ReviewStatus;
	}

	public void setReviewStatus(int reviewStatus) {
		ReviewStatus = reviewStatus;
	}

                                                                                                           }