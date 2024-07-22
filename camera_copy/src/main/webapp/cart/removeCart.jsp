<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>


<%--
	String id = request.getParameter("id");
	if (id == null || id.trim().equals("")) {
		response.sendRedirect("products.jsp");
		return;
	}

	ProductRepository dao = ProductRepository.getInstance();
	
	Product book = dao.getProductById(id);
	if (book == null) {
		response.sendRedirect("exceptionNoProductId.jsp");
	}

	ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartlist");
	Product goodsQnt = new Product();
	for (int i = 0; i < cartList.size(); i++) { // 상품리스트 하나씩 출력하기
		goodsQnt = cartList.get(i);
		if (goodsQnt.getBookID().equals(id)) {
			cartList.remove(goodsQnt);
		}
	}

	response.sendRedirect("cart.jsp");
--%>