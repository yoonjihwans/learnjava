package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//세션(Session) : 서버(웹프로그램)와 클라이언트(브라우저)의 연결 지속성을 제공하기 위해
//서버에 저장하는 값 - WAS 프로그램에 의해 관리
// => 보안 관련 정보(권한)을 저장하기 위한 세션 사용
// => 세션을 구분하기 위한 식별자(SessionId)를 사용해 클라이언트의 세션을 구분하여 서블릿에서
//사용할 수 있도록 제공 - 세션 바인딩
//세션 바인딩(Session Binding) : 연결 지속성을 제공하기 위한 값이 저장된 세션을 서블릿에서
//사용할 수 있도록 결합하는 작업 - WAS 프로그램에 의해 관리

//클라이언트로부터 [JSESSIONID]라는 이름의 쿠키값을 전달받지 못한 경우 서버에 새로운 세션을
//만들어서 서블릿에 바인딩 처리하고 생성된 세션의 식별자(SessionId)를 클라이언트에게 [JSESSIONID]
//라는 이름의 쿠키값으로 전달하여 클라이언트에 저장 
// => 클라이언트가 서블릿을 최초 요청한 경우에 대한 바인딩 처리
// => [JSESSIONID]라는 이름의 쿠키는 클라이언트 브라우저가 종료되면 소멸

//클라이언트로부터 [JSESSIONID]라는 이름의 쿠키값을 전달받은 경우 세션 트랙킹하여 바인딩 처리
// => 세션 트랙킹이 실패한 경우 새로운 세션을 만들어 바인딩 처리
//세션 트랙킹(Session Tracking) : 클라이언트로부터 전달받은 [JSESSIONID]라는 이름의 쿠키값과
//서버의 세션 식별자(SessionId)을 비교하여 검색하는 작업

//세션을 바인딩하여 클라이언트에게 바인딩된 세션정보를 HTML 문서에 포함하여 응답 처리하는 서블릿
@WebServlet("/session.itwill")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();

		//HttpServletRequest.getSession() : 바인딩된 세션(HttpSession 객체)을 반환하는 메소드
		// => 새로운 세션을 생성하여 바인딩하거나 기존 세션을 트렉킹하여 바인딩 처리
		// => HttpSession 객체 : 연결 지속성을 제공하기 위한 값(객체)를 저장하기 위한 객체 - 세션
		HttpSession session=request.getSession();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>세션(Session)</h1>");
		out.println("<hr>");
		//HttpSession.isNew() : 세션을 트렉킹하여 바인딩한 경우 [false]를 반환하고 세션을
		//생성하여 바인딩한 경우 [true]를 반환하는 메소드
		if(session.isNew()) {
			out.println("<p>세션을 생성하여 바인딩 하였습니다.</p>");
		} else {
			out.println("<p>세션을 트렉킹하여 바인딩 하였습니다.</p>");
		}
		
		//HttpSession.getId() : 세션의 식별자(SessionId)를 반환하는 메소드
		out.println("<p>세션 식별자(SessionId) = "+session.getId()+"</p>");
		
		//HttpSession.getMaxInactiveInterval() : 세션의 유지시간(초)을 반환하는 메소드
		// => 세션 유지시간 : 세션을 사용하지 않을 경우 세션이 자동으로 소멸되도록 설정된 시간
		// => 세션 유지시간을 변경하지 않으면 기본적으로 30분동안 유지되도록 설정
		out.println("<p>세션 유지시간 = "+session.getMaxInactiveInterval()+"</p>");

		//HttpSession.getCreationTime() : 세션이 생성된 시간(TimeStamp)을 반환하는 메소드
		out.println("<p>세션 유지시간 = "+session.getCreationTime()+"</p>");
		
		//HttpSession.setAttribute(String attributeName, Object attributeValue) : 매개변수로
		//전달받은 속성명(문자열)과 속성값(객체)을 세션(HttpSession 객체)에 저장하는 메소드
		// => 세션에 연결 지속성을 제공하기 위한 값(객체)를 저장하기 위한 메소드
		// => 매개변수로 전달받은 속성명과 같은 이름의 속성값이 세션에 이미 저장되어 있는
		//경우 속성값 변경하여 저장
		// => 클라이언트는 서블릿에 같은 세션을 바인딩하여 사용할 수 있기 때문에 세션에 저장된
		//속성값을 모든 서블릿이 객체로 반환받아 사용 가능 - 객체 공유
		session.setAttribute("now", new Date());
		
		//HttpSession.getAttribute(String attributeName) : 매개변수로 전달받은 속성명으로
		//세션에 저장된 속성값을 객체로 반환하는 메소드
		// => 세션에 저장된 모든 속성값은 Object 객체로 반환되므로 명시적 객체 형변환 사용
		// => 매개변수로 전달받은 속성명의 속성값이 없는 경우 [null] 반환
		Date now=(Date)session.getAttribute("now");
		out.println("<p>세션에 저장된 속성값(객체) = "+now+"</p>");
		
		//HttpSession.removeAttribute(String attributeName) : 매개변수로 전달받은 속성명으로
		//세션에 저장된 속성값(객체)을 삭제하는 메소드
		session.removeAttribute("now");
		
		//HttpSession.invalidate() : 바인딩 처리된 세션을 언바인딩 처리한 후 세션을 삭제하는 메소드
		session.invalidate();
		
		//세션을 언바인딩 처리한 후 세션에 속성값을 저장하면 IllegalStateException 발생
		//session.setAttribute("now", new Date());
		
		out.println("</body>");
		out.println("</html>");
	}
}