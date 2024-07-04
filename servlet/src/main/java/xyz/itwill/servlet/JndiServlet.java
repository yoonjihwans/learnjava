package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//JNDI(Java Naming Directory Interface) : WAS 프로그램이 실행될 때 객체를 생성하여 관리되도록
//설정하고 객체가 필요한 경우 WAS 프로그램에게 이름을 전달하여 객체를 제공받아 사용하기 위한 서비스
// => WAS 프로그램에 의해 생성되어 관리될 객체에 대한 정보를 [src/main/webapp/META-INF/context.xml]
//파일의 엘리먼트(태그)를 사용하여 제공

//WAS 프로그램의 관리되는 DataSource 객체를 제공받아 DataSource 객체로부터 Connection 객체를 
//반환받아 Connection 객체의 정보를 HTML 문서에 포함하여 응답하는 서블릿
@WebServlet("/jndi.itwill")
public class JndiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		try {
			//InitialContext 객체 : JNDI 서비스를 제공하기 위한 객체
			// => WAS 프로그램의 관리되는 객체를 반환받기 위한 메소드 제공
			//InitialContext.lookup(String name) : WAS 프로그램에 의해 관리되는 객체 중 
			//매개변수로 전달받은 이름의 객체를 반환하는 메소드
			// => Object 객체로 반환되므로 명시적 객체 형변환 사용
			// => 매개변수로 전달받은 이름의 객체가 없는 경우 NamingEception 발생
			DataSource dataSource=(DataSource)new InitialContext().lookup("java:comp/env/jdbc/oracle");
			
			Connection con=dataSource.getConnection();
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='utf-8'>");
			out.println("<title>Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>JNDI</h1>");
			out.println("<hr>");
			out.println("<p>Connection = "+con+"</p>");
			out.println("</body>");
			out.println("</html>");
			
			con.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}









