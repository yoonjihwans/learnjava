package basic;

//2차원 배열 : 1차원 배열의 모임

public class DoubleArrayApp {
    public static void main(String[] args) {
		//형식) 자료형[][] 참조변수 = new 자료형[행갯수][열갯수];
    	// = > 행갯수 : 2차원 배열에 저장될 수 있는 1차원 배열의 갯수
    	// = > 열갯수 : 2차원 배열에 저장된 1차원 배열의 요소 갯수
        int [][] numArray=new int[2][3];
        
        System.out.println("NumArray =  " + numArray);
        
       
    }
}
