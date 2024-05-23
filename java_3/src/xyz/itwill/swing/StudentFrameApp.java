package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class StudentFrameApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTable table;
	private StudentDialog studentDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrameApp frame = new StudentFrameApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentFrameApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//테이블의 컬럼명으로 출력될 문자열 배열 작성
		String[] columnNames={"학번", "이름", "전화번호"};
		
		//DefaultTableModel 클래스 : 테이블(JTable 객체)의 행 관련 정보가 저장된 객체를 생성하기 위한 클래스
		// => TableModel 객체를 이용하여 테이블의 행 또는 행의 열(Cell)을 제어하는 메소드 제공
		// => DefaultTableModel(Object[] columnNames, int rows) 생성자를 사용하여 객체 생성
		// => columnNames 매개변수에는 테이블의 컬럼명이 저장된 배열을 전달받고 rows 매개변수에는
		//행의 갯수를 전달하여 객체 생성
		DefaultTableModel tableModel=new DefaultTableModel(columnNames, 0);
		
		//JTable 클래스 : 값을 행과 열로 구성된 테이블(Table - 표) 형식으로 출력하는 컴퍼넌트를
		//생성하기 위한 클래스
		// => JTable(TableModel tm) 생성자를 사용하여 객체 생성
		// => tm 매개변수에 TableModel 객체를 전달 JTable 객체에 컬럼명과 행 출력 처리
		table = new JTable(tableModel);
		table.setFont(new Font("굴림체", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton addButton = new JButton("학생추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentDialog.setVisible(true);
			}
		});
		addButton.setFont(new Font("굴림체", Font.BOLD, 20));
		panel.add(addButton);
		
		JButton removeButton = new JButton("학생삭제");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
				
				//DefaultTableModel.getRowCount() : DefaultTableModel 객체에 저장된 행의 
				//갯수를 반환하는 메소드		
				if(tableModel.getRowCount() == 0) return;
				
				//DefaultTableModel.getSelectedRow() : JTable 컴퍼넌트에 출력된 행에서 선택된
				//행의 행번호를 반환하는 메소드
				// => 선택행이 없는 경우 [-1] 반환
				int row=table.getSelectedRow();
				if(row == -1) return;
				
				//DefaultTableModel.removeRow(int row) : 매개변수로 전달받은 행번호의 행을
				//DefaultTableModel 객체에서 삭제하는 메소드
				// => JTable 컴퍼넌트에 출력된 행 삭제 처리
				tableModel.removeRow(row);
			}
		});
		removeButton.setFont(new Font("굴림체", Font.BOLD, 20));
		panel.add(removeButton);
		
		studentDialog=new StudentDialog(this, "학생추가");				
	}

}