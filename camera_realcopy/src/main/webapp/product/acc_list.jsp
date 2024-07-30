<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="xyz.itwill.dao.ProductDAO" %>
<%@ page import="xyz.itwill.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 목록</title>
    <script src="https://kit.fontawesome.com/47ee32fc29.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;700&display=swap" rel="stylesheet">
   	  <style type="text/css">
    body {
        font-family: 'Do Hyeon', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

    .do-hyeon-regular {
        font-family: "Do Hyeon", sans-serif;
        font-weight: 400;
        font-style: normal;
    }

    main {
        padding: 20px;
    }

    .product-list {
        max-width: 1200px;
        margin: 0 auto;
    }

    .product-list h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .filter {
        text-align: right;
        margin-bottom: 20px;
    }

    .filter a {
        color: #333;
        margin: 0 10px;
        text-decoration: none;
        font-size: 14px;
    }

    .products {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }

    .product {
        background-color: white;
        border: 1px solid #ddd;
        padding: 20px;
        width: calc(25% - 20px); 
        box-sizing: border-box;
        text-align: center;
        position: relative;
    }

    .product a {
        text-decoration: none;
        color: inherit;
    }

    .product img {
        width: 100%;
        max-width: 100%;
        height: auto;
        transition: transform 0.3s ease; 
    }

    .product img:hover {
        transform: scale(1.1);  
    }

    .product h3 {
        margin: 10px 0;
        font-size: 18px;
        transition: color 0.3s;
    }

    .product h3:hover {
        color: #0a56a9;
    }

    .product .price {
        color: #000000;
        font-size: 20px;
        margin: 10px 0;
    }

    .product .filter {
        position: absolute;
        top: -30px; 
        left: 0;
        right: 0;
        text-align: center;
        background: rgba(255, 255, 255, 0); 
        padding: 5px 0;
    }

    .product .filter a {
        color: #333;
        margin: 0 10px;
        text-decoration: none;
        font-size: 14px;
    }

    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 1rem;
        font-family: 'Do Hyeon', sans-serif;
    }

    .pagination a {
        color: #333;
        padding: 0.5rem 1rem;
        text-decoration: none;
        border: 1px solid #ddd;
        margin: 0 0.2rem;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .pagination a.active,
    .pagination a:hover {
        background: #f6d365; 
        color: #333;
    }

    .floating {
        position: fixed;
        bottom: 20px;
        right: 20px;
        z-index: 1000;
    }

    .floating a {
        color: #fda085;
        font-size: 2rem;
        text-decoration: none;
    }

    .buttons {
        display: flex;
        justify-content: space-between;
    }

    .uni-btn {
        width: 100px;
        height: 40px;
        color: #fff;
        border-radius: 5px;
        padding: 0;
        font-family: 'Do Hyeon', sans-serif;
        font-weight: 500;
        background: transparent;
        cursor: pointer;
        transition: all 0.3s ease;
        position: relative;
        display: inline-block; 
        outline: none;
        border: none;
        font-size: 14px; 
        line-height: 40px; 
        text-align: center; 
    }

    .btn-basket {
        background: linear-gradient(0deg, #f6d365 0%, #fda085 100%);
        line-height: 40px;
        padding: 0;
        border: none;
    }
    .btn-basket span {
        position: relative;
        display: block;
        width: 100%;
        height: 100%;
        text-align: center;
        line-height: 40px; 
    }
    .btn-basket:before,
    .btn-basket:after {
        position: absolute;
        content: "";
        right: 0;
        bottom: 0;
        background:  #fda085;
        box-shadow:
        -7px -7px 20px 0px rgba(255,255,255,.9),
        -4px -4px 5px 0px rgba(255,255,255,.9),
        7px 7px 20px 0px rgba(0,0,0,.2),
        4px 4px 5px 0px rgba(0,0,0,.3);
        transition: all 0.3s ease;
    }
    .btn-basket:before {
        height: 0%;
        width: 2px;
    }
    .btn-basket:after {
        width: 0%;
        height: 2px;
    }
    .btn-basket:hover {
        color: #fda085;
        background: transparent;
    }
    .btn-basket:hover:before {
        height: 100%;
    }
    .btn-basket:hover:after {
        width: 100%;
    }
    .btn-basket span:before,
    .btn-basket span:after {
        position: absolute;
        content: "";
        left: 0;
        top: 0;
        background: #fda085;
        box-shadow:
        -7px -7px 20px 0px rgba(255,255,255,.9),
        -4px -4px 5px 0px rgba(255,255,255,.9),
        7px 7px 20px 0px rgba(0,0,0,.2),
        4px 4px 5px 0px rgba(0,0,0,.3);
        transition: all 0.3s ease;
    }
    .btn-basket span:before {
        width: 2px;
        height: 0%;
    }
    .btn-basket span:after {
        height: 2px;
        width: 0%;
    }
    .btn-basket span:hover:before {
        height: 100%;
    }
    .btn-basket span:hover:after {
        width: 100%;
    }

    .btn-buy {
        background: linear-gradient(0deg, #f6d365 0%, #fda085 100%);
        line-height: 40px; 
        padding: 0;
        border: none;
    }
    .btn-buy span {
        position: relative;
        display: block;
        width: 100%;
        height: 100%;
        text-align: center;
        line-height: 40px; 
    }
    .btn-buy:before,
    .btn-buy:after {
        position: absolute;
        content: "";
        right: 0;
        bottom: 0;
        background:  #fda085;
        box-shadow:
        -7px -7px 20px 0px rgba(255,255,255,.9),
        -4px -4px 5px 0px rgba(255,255,255,.9),
        7px 7px 20px 0px rgba(0,0,0,.2),
        4px 4px 5px 0px rgba(0,0,0,.3);
        transition: all 0.3s ease;
    }
    .btn-buy:before {
        height: 0%;
        width: 2px;
    }
    .btn-buy:after {
        width: 0%;
        height: 2px;
    }
    .btn-buy:hover {
        color: #fda085;
        background: transparent;
    }
    .btn-buy:hover:before {
        height: 100%;
    }
    .btn-buy:hover:after {
        width: 100%;
    }
    .btn-buy span:before,
    .btn-buy span:after {
        position: absolute;
        content: "";
        left: 0;
        top: 0;
        background: #fda085;
        box-shadow:
        -7px -7px 20px 0px rgba(255,255,255,.9),
        -4px -4px 5px 0px rgba(255,255,255,.9),
        7px 7px 20px 0px rgba(0,0,0,.2),
        4px 4px 5px 0px rgba(0,0,0,.3);
        transition: all 0.3s ease;
    }
    .btn-buy span:before {
        width: 2px;
        height: 0%;
    }
    .btn-buy span:after {
        height: 2px;
        width: 0%;
    }
    .btn-buy span:hover:before {
        height: 100%;
    }
    .btn-buy span:hover:after {
        width: 100%;
    }

    @media (max-width: 1024px) {
        .product {
            width: calc(33.33% - 20px); 
        }
    }

    @media (max-width: 768px) {
        .products {
            flex-direction: row;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .product {
            width: calc(50% - 20px); 
        }
    }

    @media (max-width: 480px) {
        header {
            padding: 10px 0;
        }

        footer {
            padding: 10px 0;
        }

        nav ul li {
            margin: 5px 0;
        }

        .product {
            width: 100%;
            margin-bottom: 15px;
        }

        .product-list h2 {
            font-size: 20px;
        }

        .product h3 {
            font-size: 16px;
        }

        .product .price {
            font-size: 18px;
        }

        .product button {
            font-size: 14px;
            padding: 8px 10px;
        }
    }
        .buttons-container {
    display: flex;
    justify-content: center; 
    gap: 5px; 
	}    
    </style>
</head>
<body>
<%
    ProductDAO productDAO = ProductDAO.getDAO();

    String filter = request.getParameter("filter");
    
    int pageNum = 1;
    int pageSize = 12; 
    if (request.getParameter("pageNum") != null) {
        try {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        } catch (NumberFormatException e) {
            pageNum = 1;
        }
    }
    int totalProducts = productDAO.getTotalProductsByType3(filter);
    int totalPage = (int) Math.ceil((double) totalProducts / pageSize);
    if (pageNum <= 0 || pageNum > totalPage) {
        pageNum = 1;
    }
    int startRow = (pageNum - 1) * pageSize + 1;
    int endRow = pageNum * pageSize;

    List<ProductDTO> products = productDAO.selectProductListByType3WithPaging(startRow, endRow, filter);
%>
<main>
    <section class="product-list">
        <h2>Acc</h2>
        <div class="filter">
            <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=acc_list&filter=new">신상품</a> | 
            <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=acc_list&filter=lowestPrice">낮은가격</a> | 
            <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=acc_list&filter=highestPrice">높은가격</a>
        </div>
        <div class="products">
            <% for (ProductDTO product : products) { %>
            <div class="product">
                <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=product_detail&prodNo=<%= product.getProdNo() %>">
                    <img src="<%=request.getContextPath()%>/product_image/<%= product.getProdImage1() %>" alt="<%= product.getProdName() %>">
                    <h3><%= product.getProdName() %></h3>
                </a>
                <p class="price">₩<%= String.format("%,d", product.getProdPrice()) %></p>
                
            <div class="buttons-container">
                <form  action="<%=request.getContextPath()%>/index.jsp?workgroup=cart&work=addtoorder" method="post">   
                <button type="submit" class="uni-btn btn-basket"><span>바로구매</span></button>
						<input type="hidden" name="prodNo" value="<%= product.getProdNo() %>">
               			<input type="hidden" id="prodAmount" name="prodAmount" value="1"> 
               	</form>
                <form  action="<%=request.getContextPath()%>/index.jsp?workgroup=cart&work=addtocart" method="post">   
                	<button type="submit" class="uni-btn btn-basket"><span>장바구니</span></button>
               	 		<input type="hidden" name="prodNo" value="<%= product.getProdNo() %>">
               			<input type="hidden" id="prodAmount" name="prodAmount" value="1"> 
               	</form>
           	</div>
                
            </div>
            <% } %>
        </div>
        <br>
        <br>
        <div class="pagination">
            <% if (pageNum > 1) { %>
                <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=acc_list&pageNum=<%= pageNum - 1 %>&filter=<%= filter %>">&laquo;</a>
            <% } %>
            <% for (int i = 1; i <= totalPage; i++) { %>
                <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=acc_list&pageNum=<%= i %>&filter=<%= filter %>" class="<%= (i == pageNum) ? "active" : "" %>"><%= i %></a>
            <% } %>
            <% if (pageNum < totalPage) { %>
                <a href="<%=request.getContextPath()%>/index.jsp?workgroup=product&work=acc_list&pageNum=<%= pageNum + 1 %>&filter=<%= filter %>">&raquo;</a>
            <% } %>
        </div>
        <div class="floating">
            <a href="#top"><i class="fa fa-arrow-circle-up"></i></a>
        </div>
    </section>
</main>
</body>
</html>
