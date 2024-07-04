package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 클라이언트 요청에 의해 WAS 프로그램으로 서블릿 클래스를 객체로 생성하여 요청 처리
//메소드를 호출하여 실행
// => 클라이언트 요청에 의한 서블릿에 대한 서블릿 객체가 이미 생성되어 있는 경우 요청 처리
//메소드만 호출하여 실행 - 기존 서블릿 객체 사용
// => WAS 프로그램을 종료하면 WAS 프로그램에 의해 생성되어 사용된 모든 서블릿 객체 소멸
// => WAS 프로그램은 서블릿 객체를 관리하는 컨테이너(Container) 기능 제공

//컨테이너(Container) : 프로그램 실행에 필요한 객체의 생명주기(LifeCycle)를 관리하는 프로그램
// => 서블릿은 컨테이너에 의해 관리되는 객체 - 컴퍼넌트(Component) : JavaBean(객체)
@WebServlet("/life.itwill")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String name;
	
	//클라이언트가 서블릿을 최초 요청할 때 WAS 프로그램에 의해 서블릿 클래스를 객체로 생성하기
	//위해 호출되는 메소드 - 생성자(Constructor)
	// => 객체를 생성하면서 실행될 초기화 명령 작성 - 필드 초기화
	public LifeCycleServlet() {
		System.out.println("### LifeCycleServlet 클래스의 기본 생성자 호출 ###");
		//name="홍길동";
	}

	//클라이언트가 서블릿을 최초 요청할 때 WAS 프로그램에 의해 서블릿 클래스를 객체로 생성한
	//후 한번만 호출되는 메소드 
	// => 객체를 생성한 후 실행될 초기화 명령 작성 - 필드 초기화
	//생성자 대신 init() 메소드를 사용하여 초기화 처리 명령을 작성하는 이유
	// => init() 메소드는 ServletConfig 객체를 제공받아 init() 메소드에서 사용 가능
	//ServletConfig 객체 : 웹자원을 생성하기 위한 환경설정 관련 정보를 저장한 객체
	// => [web.xml] 파일에서 제공되는 값을 얻어와 필드 초기화 가능 - 유지보수의 효율성 증가
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("### LifeCycleServlet 클래스의 init() 메소드 호출 ###");
		//name="홍길동";
		
		//ServletConfig.getServletContext() : ServletConfig 객체를 생성한 ServletContext 객체
		//(WAS 프로그램의 정보를 저장한 객체)를 반환하는 메소드
		//ServletContext.getInitParameter(String name) : [web.xml] 파일의 context-param 엘리먼트로
		//제공된 값을 읽어와 반환하는 메소드
		//ServletConfig.getInitParameter(String name) : [web.xml] 파일의 init-param 엘리먼트로
		//제공된 값을 읽어와 반환하는 메소드
		name=config.getServletContext().getInitParameter("name");
	}	

	//WAS 프로그램이 종료되어 서블릿 객체를 소멸하기 전에 한번만 호출되는 메소드 - 마무리 작업
	@Override
	public void destroy() {
		System.out.println("### LifeCycleServlet 클래스의 destroy() 메소드 호출 ###");
	}
	
	//클라이언트가 서블릿을 요청할 때마다 WAS 프로그램에 의해 호출되는 메소드
	// => 전달값을 반환받아 데이타 처리하고 응답 파일을 생성하는 명령 작성 - 요청 처리 메소드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("### LifeCycleServlet 클래스의 service() 메소드 호출 ###");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>서블릿의 생명주기</h1>");
		out.println("<hr>");
		out.println("<p>"+name+"님, 안녕하세요.</p>");
		out.println("</body>");
		out.println("</html>");		
	}
}