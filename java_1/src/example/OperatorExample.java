
package example;

public class OperatorExample {
	public static void main(String[] args) {
		//245678초를 일시분초 형식으로 변환하여 출력하세요.
		//2일과 20시간14분38초
		
		 
		 
		
		
		
		
		
		System.out.println("==============================================================");
		//한대의 가격이 1억 5천원만인 비행기를 20대 구매할 경우 지불해야될 금액을 계산하여 출력하세요. 
		//단, 15대 이상 구매할 경우 1대당 25%의 할인율을 적용하여 계산하세요
		int air = 150_000_000;
		int sum = 20;
		double dis = 0.25;
		double tot1 = (double)air * sum;
		
	      
		if (sum >= 15){
			 tot1 = tot1 * (1-dis);
		}    
		System.out.println(tot1);
		
	}
}
