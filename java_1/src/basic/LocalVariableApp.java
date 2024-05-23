package basic;

public class LocalVariableApp {
       public static void main(String[] args) {
		  int num1=  100;
		  
		  {
			  int num2 = 200;
			  System.out.println("========= 임의블럭 내부 =======");
		  }
	}
}
