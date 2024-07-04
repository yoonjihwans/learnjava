package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트에 저장된 모든 쿠키를 삭제하여 처리결과를 HTML 문서로 생성하여 응답하는 서블릿
@WebServlet("/remove.itwill")
public class CookieRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//클라이언트에게 전달받은 모든 쿠키를 반환받아 저장
		Cookie[] cookies=request.getCookies();
		
		if(cookies != null) {//클라이언트로부터 전달받은 쿠키가 있는 경우
			for(Cookie cookie : cookies) {
				//클라이언트로부터 전달받은 쿠키의 유지시간을 [0]으로 변경하여 클라이언트에게
				//쿠키를 전달하여 저장
				// => 유지시간이 지난 쿠키는 클라이언트에서 자동 소멸 - 쿠키 삭제
				cookie.setMaxAge(0);
				//클라이언트에게 쿠키를 전달하여 기존 쿠키 대신 유지시간이 변경된 쿠키로 저장
				response.addCookie(cookie);
			}
		}
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>쿠키 삭제</h1>");
		out.println("<hr>");
		out.println("<p>네 안에 쿠키 없다.</p>");
		out.println("<hr>");
		out.println("<p><a href='/servlet/use.itwill'>쿠키 사용</a></p>");
		out.println("</body>");
		out.println("</html>");
	}
}











