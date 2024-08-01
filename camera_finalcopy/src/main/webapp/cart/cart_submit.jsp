<%-- 주문 --%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dto.CartDTO"%>
<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dao.CartDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
UsersDTO loginUsers=(UsersDTO)session.getAttribute("loginUsers");

if(loginUsers == null) {
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
	return;	
}
if (request.getMethod().equals("GET")) {
	out.println("<script type='text/javascript'>");
	out.println("location.href='" + request.getContextPath() + "/index.jsp'");
	out.println("</script>");
	return;
}
request.setCharacterEncoding("UTF-8");

String usersId = loginUsers.getUsersId();

//장바구니 비우기
CartDAO.getDAO().clearCart(usersId);

//파라미터 값들 받아오기
//request.getParameter(name)
//목적: 지정된 이름(name)의 첫 번째 파라미터 값을 가져옵니다.
//반환 값: 해당 이름의 첫 번째 파라미터 값이 문자열로 반환됩니다. 만약 해당 이름의 파라미터가 없으면 null을 반환합니다.
//request.getParameterValues(name)
//목적: 지정된 이름(name)의 모든 파라미터 값을 배열로 가져옵니다.
//반환 값: 해당 이름의 모든 파라미터 값이 문자열 배열로 반환됩니다. 만약 해당 이름의 파라미터가 없으면 null을 반환합니다.
String[] cartno = request.getParameterValues("checkP"); 
String[] q = request.getParameterValues("countInput");
String[] cartprodNo = request.getParameterValues("prodNo");
System.out.println(cartprodNo.length);
int[] quantity = new int[q.length];

//수량 배열 초기화
for (int i = 0; i < q.length; i++) {
 quantity[i] = Integer.parseInt(q[i]);
}

//장바구니에 상품 추가
for (int i = 0; i < cartno.length; i++) {
 CartDTO cart = new CartDTO();
 cart.setCartusersId(usersId);
 cart.setCartproductNo(Integer.parseInt(cartprodNo[i])); 
 cart.setCartQuantity(quantity[i]); 
 CartDAO.getDAO().insertCart(cart);
}
request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=cart&work=cartorder0719");
%>

