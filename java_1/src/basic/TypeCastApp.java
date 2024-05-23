package basic;

public class TypeCastApp {
	public static void main(String[] args) {
		System.out.println("결과 ="+(3+1.5));
		
		int num = (int)12.3; //변수를 바꾸지않고 변수값을 바꿀때
         System.out.println("num = " + num);	
         
         int num1 = 95, num2 = 10;
         double num3 = (double)num1/num2;
         System.out.println(num3);
         
         int num4 = 100_000_000, num5 = 30; //큰 정수값 비교할때 콤마는 못쓰지만 언더바는 사용 가능하다.
         	long num6 =  (long)num4 * num5; // 결과값이 인트값을 벗어나므로 결과값도 바꿔줘야한다.
         	System.out.println(num6);
         	
         	double number=1.23456789;
         	System.out.println("number = " +number);
         	//소수점 2번째 자리까지 변환하고싶을떄
         	System.out.println("number =" + (int)(number * 100)/100.0);
         	System.out.println("number =" + (int)(number * 100)/100.0);
	}

}
