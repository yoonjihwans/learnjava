import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class admin {

    public static void main(String[] args) {
       
        JFrame frame = new JFrame("Member Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

      
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 6, 5, 5)); 
        inputPanel.add(new JLabel("이름:"));
        JTextField name = new JTextField();
        inputPanel.add(name);
        inputPanel.add(new JLabel("생년월일:"));
        JTextField birth = new JTextField();
        inputPanel.add(birth);
        inputPanel.add(new JLabel("성별:"));
        JTextField gender= new JTextField();
        inputPanel.add(gender);
        inputPanel.add(new JLabel("핸드폰:"));
        JTextField phone = new JTextField();
        inputPanel.add(phone);
        inputPanel.add(new JLabel("가입일:"));
        JTextField joinDate = new JTextField();
        inputPanel.add(joinDate);
        inputPanel.add(new JLabel("회원유형:"));
        JTextField memberType = new JTextField();
        inputPanel.add(memberType);

        topPanel.add(inputPanel, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("추가");
        JButton updateButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"no", "이름", "생년월일", "성별", "핸드폰", "회원유형", "가입일", "종료일", "남은횟수"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        
        frame.setVisible(true);
    }
}