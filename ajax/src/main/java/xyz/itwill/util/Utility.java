package xyz.itwill.util;

//웹프로그램 작성에 필요한 기능을 제공하기 위한 클래스
public class Utility {
	//문자열을 전달받아 태그 관련 문자(< 또는 >)를 회피문자로 변경하여 반환하는 정적메소드
	public static String escapeTag(String source) {
		return source.replace("<", "&lt;").replace(">", "&gt;");
	}
	
	//문자열을 전달받아 JSON 형식의 문자값으로 표현 불가능한 문자를 회피문자로 변경하여 반환하는 정적메소드
	public static String toJSON(String source) {
		return source.replace("\\", "\\\\").replace("\"", "\\\"")
					.replace("\n", "\\n").replace("\r\n", "\\n");
	}
}