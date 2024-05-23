package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//사칙 연산식을 입력받아 연산 결과를 출력하는 프로그램 작성
public class CalculatorFrameApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	//연산식을 입력받기 위한 컴퍼넌트를 저장한 필드
	private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bEquals, bPlus,
			bMinus, bMulti, bDiv, bClear;

	//연산 결과값을 출력하기 위한 컴퍼넌트를 저장한 필드
	private JLabel label;

	//연산식을 저장하기 위한 필드
	private String operation="";
	
	public CalculatorFrameApp(String title) {
		super(title);
		initButtons();
		init();
	}

	private void initButtons() {
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		bEquals = new JButton("=");
		bPlus = new JButton("+");
		bMinus = new JButton("-");
		bMulti = new JButton("*");
		bDiv = new JButton("/");
		bClear = new JButton("C");

		b0.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b1.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b2.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b3.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b4.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b5.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b6.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b7.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b8.setFont(new Font("DIALOG", Font.PLAIN, 20));
		b9.setFont(new Font("DIALOG", Font.PLAIN, 20));
		bDiv.setFont(new Font("DIALOG", Font.PLAIN, 20));
		bPlus.setFont(new Font("DIALOG", Font.PLAIN, 20));
		bMinus.setFont(new Font("DIALOG", Font.PLAIN, 20));
		bMulti.setFont(new Font("DIALOG", Font.PLAIN, 20));
		bClear.setFont(new Font("DIALOG", Font.PLAIN, 20));
		bEquals.setFont(new Font("DIALOG", Font.PLAIN, 20));
		
		b0.setBackground(Color.WHITE);
		b1.setBackground(Color.WHITE);
		b2.setBackground(Color.WHITE);
		b3.setBackground(Color.WHITE);
		b4.setBackground(Color.WHITE);
		b5.setBackground(Color.WHITE);
		b6.setBackground(Color.WHITE);
		b7.setBackground(Color.WHITE);
		b8.setBackground(Color.WHITE);
		b9.setBackground(Color.WHITE);
		bDiv.setBackground(Color.YELLOW);
		bPlus.setBackground(Color.YELLOW);
		bMinus.setBackground(Color.YELLOW);
		bMulti.setBackground(Color.YELLOW);
		bClear.setBackground(Color.GREEN);
		bEquals.setBackground(Color.CYAN);
	}
	
	private void init() {
		label = new JLabel("0");
		label.setFont(new Font("DIALOG", Font.BOLD, 30));
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setBackground(Color.LIGHT_GRAY);
		label.setForeground(Color.WHITE);

		JPanel p = new JPanel(new GridLayout(4, 4, 5, 5));
		p.setBackground(Color.BLACK);
		
		p.add(bMulti);
		p.add(bDiv);
		p.add(bPlus);
		p.add(bMinus);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.add(b7);
		p.add(b8);
		p.add(b9);
		p.add(b0);
		p.add(bEquals);
		p.add(bClear);
		
		Container container=getContentPane();
		container.setLayout(new BorderLayout(10, 10));
		container.setBackground(Color.BLACK);

		container.add(label, BorderLayout.NORTH);
		container.add(p, BorderLayout.CENTER);
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		bDiv.addActionListener(this);
		bPlus.addActionListener(this);
		bMinus.addActionListener(this);
		bMulti.addActionListener(this);
		bClear.addActionListener(this);
		bEquals.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setResizable(false);
		setBounds(500, 100, 500, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CalculatorFrameApp("계산기");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트가 발생된 컴퍼넌트(JButton 객체)를 반환받아 저장
		// => Event.getSource() 메소드는 이벤트가 발생된 컴퍼넌트를 Object 객체로 반환하므로
		//JButton 클래스의 메소드를 사용하기 위해서는 반드시 명시적 객체 형변환 필요
		JButton eventSource=(JButton)e.getSource();
		
		//이벤트가 발생된 JButton 컴퍼넌트를 비교하여 명령 선택 실행
		if(eventSource == bClear) {
			operation="";//연산식을 저장하기 위한 필드를 초기화 처리
			label.setText("0");//연산식을 출력하기 위한 JLabel 컴퍼넌트의 출력값 초기화 처리
		} else if(eventSource == bEquals) {
			//연산식(operation 필드값)에서 검색할 연산자가 저장된 문자열 배열 생성
			String[] operatorArray={"*","/","+","-"};
			
			//연산자의 시작첨자를 저장하기 위한 변수 선언
			int index=-1;
			//문자열 배열의 요소값(연산자)를 차례대로 제공받아 일괄처리할 반복문
			for(String operator : operatorArray) {
				//연산식에서 필요한 연산자를 찾아 연산자의 시작첨자를 반환받아 저장
				index=operation.indexOf(operator, 1);
				//연산식에서 연산자를 찾은 경우 반복문 종료
				if(index != -1) break;
			}
			
			//연산식에서 연산자를 찾을 수 없는 경우 이베트 처리 메소드 종료
			if(index < 0) return;
			
			try {
				//연산식에서 첫번째 피연산자를 분리하여 정수값으로 변환해 저장
				int num1=Integer.parseInt(operation.substring(0, index));

				//연산식에서 연산자를 분리하여 저장
				String operator=operation.substring(index, index+1);
				
				//연산식에서 두번째 피연산자를 분리하여 정수값으로 변환해 저장
				int num2=Integer.parseInt(operation.substring(index+1));
				
				//연산자를 비교하여 피연산자에 대한 연산결과를 계산하여 저장
				int result=0;
				switch(operator) {
				case "*": result=num1*num2; break;
				case "/": result=num1/num2; break;
				case "+": result=num1+num2; break;
				case "-": result=num1-num2; break;
				}
				
				//연산결과를 JLabel 컴퍼넌트를 사용하여 출력 처리
				//label.setText(String.valueOf(result));
				label.setText(result+"");
				 
				//operation="";//연산식을 저장하기 위한 필드를 초기화 처리
				operation=result+"";//연산 결과값을 operation 필드에 저장하여 지속적인 연산 가능
			} catch (ArithmeticException exception) {
				label.setText("[에러]0으로 나눌 수 없습니다.");
				operation="";
			} catch (NumberFormatException exception) {
				//JOptionPane 클래스 : 다양한 다이얼로그를 제공하는 클래스
				//JOptionPane.showMessageDialog(Component parent, String message) : 메세지
				//다이얼로그를 제공하여 메세지를 출력하기 위한 정적메소드 
				JOptionPane.showMessageDialog(this, "입력한 연산식이 형식에 맞지 않습니다.");
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, "프로그램 실행에 예기치 못한 오류가 발생 되었습니다.");
				System.exit(0);
			}
		} else {//bClear 및 bEquals의 JButon 컴퍼넌트를 제외한 나머지 JButon 컴퍼넌트에서 이벤트가 발생된 경우 
			//JButton 컴퍼넌트의 라벨명을 반환받아 operation 필드에 추가하여 저장
			// => 버튼(JButton 컴퍼넌트)를 눌러 연산식을 완성하여 필드에 저장
			operation+=eventSource.getText();
			
			//JLabel 컴퍼넌트의 출력값을 operation 필드값으로 변경하여 출력 처리
			label.setText(operation);
		}
	}
}












