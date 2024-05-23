package xyz.itwill.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WindowBuilderApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderApp frame = new WindowBuilderApp();
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
	public WindowBuilderApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 200, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 3, 3, 0));
		
		redButton = new JButton("빨간색");
		redButton.setForeground(new Color(255, 0, 0));
		redButton.setFont(new Font("굴림체", Font.BOLD, 20));
		panel.add(redButton);
		
		greenButton = new JButton("초록색");
		greenButton.setForeground(new Color(0, 255, 0));
		greenButton.setFont(new Font("굴림체", Font.BOLD, 20));
		panel.add(greenButton);
		
		blueButton = new JButton("파란색");
		blueButton.setForeground(new Color(0, 0, 255));
		blueButton.setFont(new Font("굴림체", Font.BOLD, 20));
		panel.add(blueButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFocusable(false);
		textArea.setFont(new Font("굴림체", Font.BOLD, 20));
		scrollPane.setViewportView(textArea);
		
		redButton.addActionListener(new ButtonEventHandle());
		greenButton.addActionListener(new ButtonEventHandle());
		blueButton.addActionListener(new ButtonEventHandle());
		
		JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=textField.getText();
				if(!text.equals("")) {
					textArea.append(text+"\n");
					textArea.setCaretPosition(textArea.getText().length());
					textField.setText("");
				}
			}
		});
		
		textField.setFont(new Font("굴림체", Font.BOLD, 20));
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
	}
	
	public class ButtonEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton eventSource=(JButton)e.getSource();
			
			redButton.setEnabled(true);
			greenButton.setEnabled(true);
			blueButton.setEnabled(true);

			eventSource.setEnabled(false);
			
			if(eventSource == redButton) {
				textArea.setForeground(Color.RED);
			} else if(eventSource == greenButton) {
				textArea.setForeground(Color.GREEN);
			} else if(eventSource == blueButton) {
				textArea.setForeground(Color.BLUE);
			}
		}
	}
}



