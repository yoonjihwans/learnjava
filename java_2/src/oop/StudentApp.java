package oop;

public class StudentApp {
	public static void main(String[] args) {
		/*
		Student student1=new Student(1000, "홍길동", 90, 90);
		Student student2=new Student(2000, "임꺽정", 94, 98);
		Student student3=new Student(3000, "전우치", 91, 80);
		Student student4=new Student(4000, "일지매", 76, 82);
		Student student5=new Student(5000, "장길산", 84, 86);
		
		student1.display();
		student2.display();
		student3.display();
		student4.display();
		student5.display();
		System.out.println("==============================================================");
		student1.setKor(100);
		student1.calcTot();
		student1.display();
		System.out.println("==============================================================");
		*/
		
		/*
		//객체를 저장할 수 있는 요소가 5개 배열을 생성하여 참조변수에 저장
		// => 모든 배열 요소에는 기본값으로 [null] 저장
		Student[] students=new Student[5];
		
		students[0]=new Student(1000, "홍길동", 90, 90);
		students[1]=new Student(2000, "임꺽정", 94, 98);
		students[2]=new Student(3000, "전우치", 91, 80);
		students[3]=new Student(4000, "일지매", 76, 82);
		students[4]=new Student(5000, "장길산", 84, 86);
		
		//반복문을 사용하여 배열 요소에 저장된 모든 객체를 참조해 메소드 호출 가능 - 일괄처리
		// => 배열 요소에 [null]이 저장된 상태에서 메소드를 호출할 경우 NullPointerException 발생
		//NullPointerException : 참조변수에 [null]이 저장된 상태에서 메소드를 호출할 경우 
		//참조할 객체가 없기 때문에 발생되는 예외
		for(int i=0;i<students.length;i++) {
			//NullPointException 발생을 방지하기 위한 선택문
			if(students[i] != null) {//배열 요소값이 [null]이 아닌 경우에만 메소드 호출
				students[i].display();
			}
		}
		System.out.println("==============================================================");
		*/
		//int total=0;
		
		Student[] students={new Student(1000, "홍길동", 90, 90)
				, new Student(2000, "임꺽정", 94, 98), new Student(3000, "전우치", 91, 80)
				, new Student(4000, "일지매", 76, 82), new Student(5000, "장길산", 84, 86)};
		
		//향상된 for 구문을 사용하여 배열 요소가 참조하는 객체를 차례대로 제공받아 일괄 처리 가능
		for(Student student : students) {
			student.display();
			//total+=student.getTot();
			//Student.total+=student.getTot();
			Student.setTotal(Student.getTotal()+student.getTot());
		}
		System.out.println("==============================================================");
		//모든 학생들의 성적 합계를 계산하여 출력
		//System.out.println("총합계 = "+total);
		//System.out.println("총합계 = "+Student.total);
		System.out.println("총합계 = "+Student.getTotal());
		System.out.println("==============================================================");
	}
}