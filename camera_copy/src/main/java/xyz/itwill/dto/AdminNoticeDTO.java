package xyz.itwill.dto;

public class AdminNoticeDTO {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private int noticeStatus;
	private String noticeDate;
	
	public AdminNoticeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AdminNoticeDTO(int noticeNo, String noticeTitle, String noticeContent, int noticeStatus, String noticeDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeStatus = noticeStatus;
		this.noticeDate = noticeDate;
	}


	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(int noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	
	

}
