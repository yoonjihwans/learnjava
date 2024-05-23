package inheritance;


//시간제 사원정보(사원번호, 사원이름, 시급, 근무시간)를 저장하기 위한 클래스 //시급 * 근무시간
//= > Employee 클래스를 상속받아 작성
public class EmployeeTime extends Employee {
	private int moneyPerHour; //시급
	private int WorkedHour; //근무시간
	
	public EmployeeTime() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeTime(int empNo, String empName, int moneyPerHour, int workedHour) {
		super(empNo, empName);
		this.moneyPerHour = moneyPerHour;
		WorkedHour = workedHour;
	}

	public int getMoneyPerHour() {
		return moneyPerHour;
	}

	public void setMoneyPerHour(int moneyPerHour) {
		this.moneyPerHour = moneyPerHour;
	}

	public int getWorkedHour() {
		return WorkedHour;
	}

	public void setWorkedHour(int workedHour) {
		WorkedHour = workedHour;
	}
	
	//급여를 계산하여 반환하는 메소드
//	public int computeTimePay() {
//		return moneyPerHour*WorkedHour;
//	}
@Override
public int computePay() {
	return moneyPerHour*WorkedHour;
}	

}
