<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달값(회원정보)을 반환받아 내장객체의 속성값으로 저장하고 [useBean_display.jsp] 문서로 
포워드 이동하는 JSP 문서 - 클라이언트 요청에 대한 데이타 처리 기능만 제공하는 JSP 문서 --%>    
<%
	//JSP 문서를 GET 방식으로 요청한 경우 에러코드를 클라이언트에게 전달하여 응답 처리
	if(request.getMethod().equals("GET")) {//JSP 문서를 비정상적으로 요청한 경우
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

	//POST 방식으로 요청하여 전달된 값을 읽기 위한 문자형태 변경
	request.setCharacterEncoding("utf-8");
%>
<%-- useBean 태그 : JSP 문서에 Java 객체(JavaBean)를 제공하기 위한 태그 --%>
<%-- 형식) <jsp:useBean id="식별자" class="클래스" scope="사용범위"></jsp:useBean> --%>
<%-- => 내장객체에 저장된 속성값을 객체로 반환받아 제공하거나 속성값이 없는 경우 객체를
생성하여 내장객체의 속성값으로 저장한 후 제공 --%>
<%-- id 속성 : useBean 태그에 제공되는 객체를 구분하기 위한 식별자를 속성값으로 설정 --%>
<%-- => 내장객체에 저장된 속성값에 대한 속성명으로 사용 --%>
<%-- class 속성 : useBean 태그에 제공되는 객체의 자료형(클래스)을 속성값으로 설정 --%>
<%-- scope 속성 : useBean 태그에 제공되는 객체의 사용범위를 속성값으로 설정 --%>
<%-- => page, request, session, application 중 하나를 속성값으로 설정 --%>
<%-- => scope 속성을 생략한 경우 [page] 속성값을 기본값으로 사용 --%>
<jsp:useBean id="hewon" class="xyz.itwill.bean.HewonBean" scope="request"/>
<%--
	//useBean 태그를 사용하면 HewonBean 객체를 생성하여 request 객체의 속성값으로 저장하는
	//명령과 동일한 기능을 제공
	HewonBean hewon=new HewonBean();
	request.setAttribute("hewon", hewon);
--%>

<%-- setProperty 태그 : useBean 태그로 제공받은 객체의 필드값을 변경하는 태그 --%>
<%-- => useBean 태그의 종속된 태그 --%>
<%-- 형식) <jsp:setProperty name="식별자" property="필드명" value="변경값"></jsp:setProperty> --%>
<%-- => 객체로 Setter 메소드를 호출하여 필드값을 변경하는 기능과 동일 --%>
<%-- name 속성 : useBean 태그로 제공받은 객체의 식별자(id 속성값)를 속성값으로 설정 --%>
<%-- property 속성 : 필드값을 변경하기 위한 필드명을 속성값으로 설정 --%>
<%-- => 속성값으로 설정된 필드의 Setter 메소드를 호출하여 필드값 변경 - Setter 메소드가 없는 경우 에러 발생 --%>
<%-- value 속성 : 필드에 저장될 필드값을 속성값으로 설정 --%>
<%-- <jsp:setProperty name="hewon" property="name" value="홍길동"/> --%>
<%-- hewon.setName("홍길동"); --%>

<%-- setProperty 태그의 value 속성을 생략하면 JSP 문서 요청시 전달된 값을 반환받아 필드값으로 변경 --%>
<%-- => 전달값의 이름과 같은 이름의 필드가 있어야 전달값을 반환받아 필드값으로 변경 가능 --%>
<%-- 
<jsp:setProperty name="hewon" property="name"/>
<jsp:setProperty name="hewon" property="phone"/>
<jsp:setProperty name="hewon" property="address"/>
--%>
<%-- property 속성값으로 [*] 기호를 설정하면 모든 전달값을 반환받아 전달값의 이름과 같은 이름의
필드의 필드값을 변경 처리 --%>
<jsp:setProperty name="hewon" property="*"/>
<%--
	hewon.setName(request.getParameter("name"));
	hewon.setPhone(request.getParameter("phone"));
	hewon.setAddress(request.getParameter("address"));
--%>

<jsp:forward page="/action/useBean/useBean_display.jsp"/>
<%-- request.getRequestDispatcher("/action/useBean/useBean_display.jsp").forward(request, response); --%>	s