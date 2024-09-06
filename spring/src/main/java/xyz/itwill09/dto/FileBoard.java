package xyz.itwill09.dto;

import lombok.Data;

//create table file_board(num number primary key, writer varchar2(20)
//	, subject varchar2(100), filename varchar2(200));
//create sequence file_board_seq;   
@Data
public class FileBoard {
	private int num;
	private String writer;
	private String subject;
	private String filename;
}