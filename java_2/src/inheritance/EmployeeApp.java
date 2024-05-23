package inheritance;

//사원 급여 관리 프로그램 작성
public class EmployeeApp {
	public static void main(String[] args) {
		/*
		//Employee 클래스가 추상클래스로 선언되어 있는 경우 객체 생성시 에러 발생
		Employee employee=new Employee(1000, "홍길동");
		System.out.println("사원번호 = "+employee.getEmpNo());
		System.out.println("사원이름 = "+employee.getEmpName());
		*/
		
		/*
		//추상클래스로 참조변수를 생성하여 자식클래스의 객체를 저장 가능
		Employee employee1=new EmployeeRegular();
		Employee employee2=new EmployeeTime();
		Employee employee3=new EmployeeContract();
		*/
		
		//Employee 클래스를 상속받은 자식클래스의 객체를 저장할 수 있는 요소를 가진 배열 생성
		Employee[] empArray=new Employee[5];
		
		empArray[0]=new EmployeeRegular(1000, "홍길동", 96000000);
		empArray[1]=new EmployeeTime(2000, "임꺽정", 50000, 150);
		empArray[2]=new EmployeeContract(3000, "전우치", 7000000);
		empArray[3]=new EmployeeTime(4000, "일지매", 20000, 100);
		empArray[4]=new EmployeeRegular(5000, "장길산", 60000000);
		
		for(Employee employee : empArray) {
			System.out.println("사원번호 = "+employee.getEmpNo());
			System.out.println("사원이름 = "+employee.getEmpName());
			
			/*
			//급여를 계산하여 반환하는 메소드를 호출해 급여를 반환받아 출력
			// => 부모클래스로 생성된 참조변수이므로 부모클래스의 메소드만 호출 가능하지만
			//자식클래스의 메소드 호출 불가능
			// => 부모클래스의 참조변수로 자식클래스의 메소드를 호출하기 위해서는 객체 형변환 사용
			//자식클래스가 여러개인 경우 부모클래스의 참조변수가 객체 형변환 가능한지를 확인하기
			//위해 instanceof 연산자으로 비교한 후 명시적 객체 형변환 사용 - ClassCastException 발지
			if(employee instanceof EmployeeRegular) {
				System.out.println("사원급여 = "+((EmployeeRegular)employee).computeSalary());
			} else if(employee instanceof EmployeeTime) {
				System.out.println("사원급여 = "+((EmployeeTime)employee).computeTimePay());
			} else if(employee instanceof EmployeeContract) {
				System.out.println("사원급여 = "+((EmployeeContract)employee).computeConstract());
			} 
			*/ 

			//묵시적 객체 형변환에 의해 부모클래스의 참조변수가 일시적으로 자식클래스의 객체를
			//참조하여 자식클래스의 오버라이딩 선언된 메소드를 호출
			System.out.println("사원급여 = "+employee.computePay());
			System.out.println("인센티브 = "+employee.computeIncentive());
			System.out.println("==========================================================");
		}
	}
}












