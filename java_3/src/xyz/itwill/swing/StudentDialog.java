package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StudentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField numTF;
	private JTextField nameTF;
	private JTextField phoneTF;

	public StudentDialog(JFrame frame, String title) {
		//부모클래스(JDialog 클래스)의 JDialog(Frame owner, String title, boolean model) 
		//생성자를 super 키워드로 호출하여 JDialog 객체 생성
		// => owner 매개변수에는 부모창(JFrame 객체)을 전달받고 model 매개변수에는 부모창의
		//비활성화 여부를 설정하는 논리값(false : 부모창 활성화 - Modeless Dialog, true : 
		//부모창 비활성화 - Model Dialog)을 전달받아 JDialog 객체 생성
		super(frame, title, true);
		
		setBounds(700, 200, 450, 300);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				numTF.setText("");
				nameTF.setText("");
				phoneTF.setText("");
				setVisible(false);
			}
		});
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {100, 300};
		gbl_contentPanel.rowHeights = new int[] {50, 50, 50};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("학번");
			lblNewLabel.setFont(new Font("굴림체", Font.BOLD, 20));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			numTF = new JTextField();
			numTF.setFont(new Font("굴림체", Font.PLAIN, 20));
			GridBagConstraints gbc_numTF = new GridBagConstraints();
			gbc_numTF.fill = GridBagConstraints.HORIZONTAL;
			gbc_numTF.insets = new Insets(0, 0, 5, 0);
			gbc_numTF.gridx = 1;
			gbc_numTF.gridy = 0;
			contentPanel.add(numTF, gbc_numTF);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("이름");
			lblNewLabel_1.setFont(new Font("굴림체", Font.BOLD, 20));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			nameTF = new JTextField();
			nameTF.setFont(new Font("굴림체", Font.PLAIN, 20));
			GridBagConstraints gbc_nameTF = new GridBagConstraints();
			gbc_nameTF.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTF.insets = new Insets(0, 0, 5, 0);
			gbc_nameTF.gridx = 1;
			gbc_nameTF.gridy = 1;
			contentPanel.add(nameTF, gbc_nameTF);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("전화번호");
			lblNewLabel_2.setFont(new Font("굴림체", Font.BOLD, 20));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			phoneTF = new JTextField();
			phoneTF.setFont(new Font("굴림체", Font.PLAIN, 20));
			GridBagConstraints gbc_phoneTF = new GridBagConstraints();
			gbc_phoneTF.fill = GridBagConstraints.HORIZONTAL;
			gbc_phoneTF.gridx = 1;
			gbc_phoneTF.gridy = 2;
			contentPanel.add(phoneTF, gbc_phoneTF);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("추가");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//JTextField 컴퍼넌트의 입력값을 반환받아 저장
						String num=numTF.getText();
						String name=nameTF.getText();
						String phone=phoneTF.getText();
						
						//Vector 객체를 생성하여 입력값을 요소에 저장하여 추가
						// => Vector 객체가 JTable 컴퍼넌트의 하나의 행으로 출력
						Vector<String> vector=new Vector<String>();
						vector.add(num);
						vector.add(name);
						vector.add(phone);
						
						//JTable.getModel() : JTable 컴퍼넌트에 저장된 TableModel 객체를 
						//반환하는 메소드
						TableModel tableModel=((StudentFrameApp)frame).table.getModel();
						
						//DefaultTableModel.addRow(Vector vector) : 매개변수로 전달받은 
						//Vector 객체를 JTable 컴퍼넌트의 행으로 추가하는 메소드
						// => Vector 객체의 요소값이 행을 구성하는 열의 값으로 처리
						((DefaultTableModel)tableModel).addRow(vector);
						
						numTF.setText("");
						nameTF.setText("");
						phoneTF.setText("");
						setVisible(false);
					}
				});
				okButton.setFont(new Font("굴림체", Font.BOLD, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//JTextField 컴퍼넌트의 입력값 초기화
						numTF.setText("");
						nameTF.setText("");
						phoneTF.setText("");
						//JDialog 컨테이너 숨김 처리
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("굴림체", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}