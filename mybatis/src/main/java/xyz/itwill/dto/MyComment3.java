package xyz.itwill.dto;

public class MyComment3 {
	private int no;
	private String id;
	private String content;
	private String date;

	private String name;

	public MyComment3() {
		// TODO Auto-generated constructor stub
	}

	public MyComment3(int no, String id, String content, String date, String name) {
		super();
		this.no = no;
		this.id = id;
		this.content = content;
		this.date = date;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
