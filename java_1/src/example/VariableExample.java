package example;

public class VariableExample {
	public static void main(String[] args) {
		//가로의 길이가 7이고 세로의 길이가 10인 사각형의 넓이를 계산하여 출력하세요.
		int num1 = 7, num2 = 10;
		int tot = num1 * num2;
		System.out.println("사각형의 넓이는 = " + tot);
		System.out.println("==============================================================");
		//높이가 9이고 밑변의 길이가 7인 삼각형의 넓이를 계산하여 출력하세요.
		int height = 9; int down = 7;
		double sum = down * height / 2.;
		System.out.println("삼각혀의 넓이는 = " + sum);
		System.out.println("==============================================================");
		//10명의 전체 몸무게가 759Kg인 경우 평균 몸무게를 계산하여 출력하세요.
	     int count = 10,  rul = 759; //변수를 활용할것!!
	     double weight =(double)rul/count; //값을 넣지말고 변수를 잘 활용할것
	     System.out.println("평균 몸무게는 = " + weight);
		System.out.println("==============================================================");
		//이름이 [홍길동]인 학생이 국어점수 89점, 영어점수 93점, 수학점수 95점을 받은 경우
		//총점과 평균을 계산하여 이름, 총점, 평균을 출력하세요.
		//단, 평균은 소숫점 한자리까지만 출력하고 나머지는 절삭 처리 하세요.
		   String str = "홍길동"; //이름도 String으로 변수 선언해서 값 넣어주기!!
           int korean = 89, english = 93, math = 95;
           int sumnumber = korean + english + math;
           double summiddle =  (double)sumnumber / 3;
           System.out.println( str + "의 총점은 = " + sumnumber + " 평균은 = " + (int)(summiddle * 10)/10.);
	System.out.println("==============================================================");
	}
}