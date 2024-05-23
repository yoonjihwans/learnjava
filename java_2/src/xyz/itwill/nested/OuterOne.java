package xyz.itwill.nested;

//중첩클래스(Nested Class) : 클래스(Outer Class)에 선언된 클래스(Inner Class)
// => 두개의 클래스가 밀접한 관계에 있는 경우 사용 - 이벤트 처리 프로그램 작성시 사용
// => 클래스의 캡슐화를 강화할 목적으로 중첩클래스 선언

public class OuterOne {
	private int outerNum;
	
	public OuterOne() {
		// TODO Auto-generated constructor stub
	}

	public OuterOne(int outerNum) {
		super();
		this.outerNum = outerNum;
	}

	public int getOuterNum() {
		return outerNum;
	}

	public void setOuterNum(int outerNum) {
		this.outerNum = outerNum;
	}
	
	public void outerDisplay() {
		System.out.println("outerNum = "+outerNum);
		
		//외부클래스의 메소드에서는 객체 내부클래스의 필드 또는 메소드를 직접 참조하여 사용 불가능
		//System.out.println("innerNum = "+innerNum);
		//innerDisplay();
		
		//외부클래스의 메소드에서는 객체 내부클래스로 객체를 생성하면 접근 제한자에 상관없이
		//내부클래스의 객체를 참조해 필드 또는 메소드 사용 가능
		//InnerOne innerOne=new InnerOne(100);
		//System.out.println("innerOne.innerNum = "+innerOne.innerNum);
		//innerOne.innerDisplay();
	}

	//객체 내부클래스(Instance Inner Class)
	// => 객체 내부클래스는 컴파일되면 [외부클래스$내부클래스.class] 파일로 생성
	// => 외부클래스에 종속되어 사용되는 클래스 - 외부클래스에서만 사용 가능한 클래스
	public class InnerOne {
		private int innerNum;
		
		public InnerOne() {
			// TODO Auto-generated constructor stub
		}

		public InnerOne(int innerNum) {
			super();
			this.innerNum = innerNum;
		}

		public int getInnerNum() {
			return innerNum;
		}

		public void setInnerNum(int innerNum) {
			this.innerNum = innerNum;
		}
		
		public void innerDisplay() {
			System.out.println("innerNum = "+innerNum);
			
			//객체 내부클래스의 메소드에서는 외부클래스의 필드 또는 메소드를 접근 제한자에
			//상관없이 직접 참조하여 사용 가능
			//System.out.println("outerNum = "+outerNum);
			//outerDisplay();
		}
	}
}

















