<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 서버 플렛폼의 날짜와 시간 및 음원목록을 JSON 형식의 데이타로 응답하는 JSP 문서 --%>
<%
	String now=new SimpleDateFormat("yyyy년 MM월 dd일 HH시").format(new Date());
%>
{
	"now":"<%=now %>",
	"songs":[
		{"title":"Supernova","singer":"aespa"}
		,{"title":"Small girl(feat. 도경수(D.O.))","singer":"이영지"}
		,{"title":"How Sweet","singer":"NewJeans"}
		,{"title":"Supernatural","singer":"NewJeans"}
		,{"title":"소나기","singer":"이클립스(ECLIPSE)"}
	]
}