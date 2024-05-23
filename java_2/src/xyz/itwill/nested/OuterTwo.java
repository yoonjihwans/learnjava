package xyz.itwill.nested;

public class OuterTwo {
	private int outerNum;
	
	public OuterTwo() {
		// TODO Auto-generated constructor stub
	}

	public OuterTwo(int outerNum) {
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
		
		//외부클래스의 메소드에서는 정적 내부클래스로 객체를 생성하면 접근 제한자에 상관없이
		//내부클래스의 객체를 참조해 필드 또는 메소드 사용 가능
		//InnerTwo innerTwo=new InnerTwo(100);
		//System.out.println("innerTwo.innerNum = "+innerTwo.innerNum);
		//innerTwo.innerDisplay();;

	}
	
	//정적 내부클래스(Static Inner Class) : static 제한자를 사용하여 선언된 내부클래스
	// => 외부클래스의 객체와 상관없이 독립적으로 사용될 수 있는 내부클래스
	public static class InnerTwo {
		private int innerNum;
		
		public InnerTwo() {
			// TODO Auto-generated constructor stub
		}

		public InnerTwo(int innerNum) {
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
			
			//외부클래스의 객체를 참조할 수 없으므로 외부클래스의 필드 또는 메소드 사용 불가능
			// => 외부클래스에 작성된 정적필드 및 정적메소드는 외부클래스를 참조하여 사용 가능 
			//System.out.println("outerNum = "+outerNum);
			//outerDisplay();
		}
	}
}











