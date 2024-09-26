package xyz.itwill.dto;

import lombok.Data;

//create table security_board(num number primary key, writer varchar2(100)
//	    , subject varchar2(500), content varchar2(4000), regdate date);
//create sequence security_board_seq;

@Data
public class SecurityBoard {
	private int num;
	private String writer;//작성자(아이디)
	private String subject;
	private String content;
	private String regdate;
	
	private String name;//작성자(이름)
}
