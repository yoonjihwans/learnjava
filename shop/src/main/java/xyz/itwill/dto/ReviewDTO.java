package xyz.itwill.dto;

/*
create table review(review_num number primary key, review_member_num number constraint 
    review_member_num_fk references member(member_num), review_subject varchar2(500)
    , review_content varchar2(4000), review_image varchar2(100), review_register_date date
    , review_update_date date, review_ip varchar2(20), review_count number, review_ref number
    , review_restep number , review_relevel number, review_status number(1));
   
create sequence review_seq; 
*/

/*
이름                   널?       유형             
-------------------- -------- -------------- 
REVIEW_NUM           NOT NULL NUMBER         - 글번호         
REVIEW_MEMBER_NUM             NUMBER         - 작성자 : 로그인 사용자의 회원번호
REVIEW_SUBJECT                VARCHAR2(500)  - 제목
REVIEW_CONTENT                VARCHAR2(4000) - 내용 
REVIEW_IMAGE                  VARCHAR2(100)  - 이미지 파일의 이름 
REVIEW_REGISTER_DATE          DATE           - 작성날짜
REVIEW_UPDATE_DATE            DATE           - 변경날짜
REVIEW_IP			          VARCHAR2(20)   - 클라이언트 IP 주소    
REVIEW_COUNT                  NUMBER         - 조횟수
REVIEW_REF                    NUMBER         - 답글 : 글그룹
REVIEW_RESTEP                 NUMBER         - 답글 : 글순서
REVIEW_RELEVEL                NUMBER         - 답글 : 글깊이
REVIEW_STATUS                 NUMBER(1)      - 글상태 : 0(삭제글), 1(일반글), 2(비밀글)
*/

public class ReviewDTO {
	private int reviewNum;
	private int reviewMemberNum;
	private String memberName;
	private String reviewSubject;
	private String reviewContent; 
	private String reviewImage;
	private String reviewRegisterDate;
	private String reviewUpdateDate;
	private String reviewIp;
	private int reviewCount;
	private int reviewRef;
	private int reviewRestep;
	private int reviewRelevel;
	private int reviewStatus;

	public ReviewDTO() {
		// TODO Auto-generated constructor stub
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
	
	

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getReviewSubject() {
		return reviewSubject;
	}

	public void setReviewSubject(String reviewSubject) {
		this.reviewSubject = reviewSubject;
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

	public String getReviewRegisterDate() {
		return reviewRegisterDate;
	}

	public void setReviewRegisterDate(String reviewRegisterDate) {
		this.reviewRegisterDate = reviewRegisterDate;
	}

	public String getReviewUpdateDate() {
		return reviewUpdateDate;
	}

	public void setReviewUpdateDate(String reviewUpdateDate) {
		this.reviewUpdateDate = reviewUpdateDate;
	}

	public String getReviewIp() {
		return reviewIp;
	}

	public void setReviewIp(String reviewIp) {
		this.reviewIp = reviewIp;
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
		return reviewRelevel;
	}

	public void setReviewRelevel(int reviewRelevel) {
		this.reviewRelevel = reviewRelevel;
	}

	public int getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
}