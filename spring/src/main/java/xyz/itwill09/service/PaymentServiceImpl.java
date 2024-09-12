package xyz.itwill09.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import xyz.itwill09.dto.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void addPayment(Payment payment) {
		System.out.println("결재정보를 제공받아 PAYMENT 테이블의 행으로 삽입하는 명령 실행- DAO 클래스의 메소드 호출");
	}

	//OpenAPI를 사용하기 위한 토큰을 발급받아 반환하는 메소드
	@Override
	public String getAccessToken(Payment payment) {
		String accessToken="";
		
		//토큰 발급을 요청하기 위한 OpenAPI의 URL 주소 저장
		String apiURL="https://api.iamport.kr/users/getToken";
		
		//OpenAPI에 전달될 값을 JSON 형식의 문자열로 작성하여 저장
		// => {"imp_key" : REST API Key, "imp_secret" : REST API Secret}
		String data="{\"imp_key\" : \"7145387726131117\", \"imp_secret\" : \"p6hCDrAyOWsAd4wn5e6kN6L2Si3yT1wI8cUivJDq0YasIVqxucrW9BWy4DTE9Yng8iEkrFMnDohOTEe3\"}";
	
		try {
			//OpenAPI의 URL 주소가 저장된 URL 객체 생성
			URL url=new URL(apiURL);
			
			//URL.openConnection() : URL 객체에 저장된 url 주소를 사용하여 서버에 접속해 
			//OpenAPI를 요청할 수 있는 정보가 저장된 HttpURLConnection 객체를 반환하는 메소드
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			
			//HttpURLConnection.setRequestProperty(String key, String value) : 리퀘스트 메세지
			//머릿부를 변경하는 메소드
			// => 전달값을 JSON 형식의 문자열로 전달되도록 변경
			connection.setRequestProperty("Content-type", "application/json");
			
			//HttpURLConnection.setRequestMethod(String method) : HttpURLConnection 객체에
			//저장된 요청정보로 페이지를 요청하기 위한 요청방식을 변경하는 메소드
			// => OpenAPI를 [post] 방식으로 요청할 수 있도록 변경
			connection.setRequestMethod("post");
			
			//HttpURLConnection.setDoInput(boolean doInput) : 응답결과의 제공여부를 변경하는 메소드
			// => OpenAPI의 실행결과를 응답받도록 변경
			connection.setDoInput(true);
			
			//OpenAPI 요청에 필요한 값을 전달하기 위한 출력스트림을 반환바아 저장
			try(OutputStream out=connection.getOutputStream()) {
				byte[] requestData=data.getBytes();
				out.write(requestData);//출력스트림을 사용해 OpenAPI에게 값 전달
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//응답코드를 반환받아 저장
			int responseCode=connection.getResponseCode();
			
			if(responseCode == 200) {
				//응답결과를 제공받기 위한 입력스트림을 반환받아 저장 - 확장
				BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				//입력스트림을 사용해 응답결과를 제공받아 저장
				String input="";
				String result="";
				while((input = br.readLine()) != null) {
					result+=input;
				}
				br.close();
				
				/*
				{
				    "code": 0,
				    "message": null,
				    "response": {
				    	"access_token": "2acc2a060141dda4070b3bd46ff19797296480f2",
				    	"now": 1726128309,
				    	"expired_at": 1726130109
				   	}
				}
				*/
				
				//JSON 형식의 문자열을 Java 객체로 변환하는 기능을 제공하는 json-simple
				//라이브러리를 프로젝트에 빌드 처리 - 메이븐 : pom.xml
				//JSONParser 객체 : JSON 형식의 문자열을 Java 객체로 변환하는 기능을 제공하는 객체
				JSONParser jsonParser=new JSONParser();
				
				//JSONParser.parse(String json) : 매개변수로 전달받은 JSON 형식의 문자열을 
				//Java 객체(Object 객체)로 변환하여 반환하는 메소드
				// => 반환받은 Object 객체를 JSONObject 객체(자바스크립트의 Object 객체로 유사)로
				//명시적 객체 형변환하여 저장
				JSONObject jsonObject=(JSONObject)jsonParser.parse(result);
				
				//JSONObject.get(String name) : JSONObject 객체에서 매개변수로 전달받은 
				//속성명의 속성값(Object 객채)을 반환하는 메소드 
				JSONObject responseObject=(JSONObject)jsonObject.get("response");
				
				accessToken=(String)responseObject.get("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	//하나의 결재정보를 검색하여 제공하는 OpenAPI를 사용해 결재정보를 반환하는 메소드
	@Override
	public Payment getPayment(String accessToken, String impUid) {
		// TODO Auto-generated method stub
		return null;
	}

	//결재정보를 취소하는 OpenAPI를 사용해 취소결과를 반환하는 메소드
	@Override
	public String cancelPayment(String accessToken, Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}
	
}