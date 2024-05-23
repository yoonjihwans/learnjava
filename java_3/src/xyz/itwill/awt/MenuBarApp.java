package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.KeyEvent;

public class MenuBarApp extends Frame {
	private static final long serialVersionUID = 1L;

	public MenuBarApp(String title) {
		super(title);
		
		//MenuBar 클래스 : 프레임(Frame 객체)에 메뉴바로 배치할 수 있는 객체를 생성하는 클래스
		MenuBar menuBar=new MenuBar();
		
		//Menu 클래스 : 메뉴바(MenuItem 객체)에 메뉴로 배치할 수 있는 객체를 생성하기 위한 클래스
		Menu file=new Menu("File");
		Menu help=new Menu("Help");
		
		//MenuItem 클래스 : 메뉴(Menu 객체)에 아이템으로 배치할 수 있는 객체를 생성하기 위한 클래스
		//MenuShortcut 클래스 : 메뉴아이템의 단축키를 저장한 객체를 생성하기 위한 클래스
		//KeyEvent 클래스 : 키보드 관련 이벤트 정보를 저장한 객체를 생성하기 위한 클래스
		// => 가상의 키보드를 이용하여 키보드에 대한 정보를 상수필드로 제공
		MenuItem open=new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
		MenuItem save=new MenuItem("Save", new MenuShortcut(KeyEvent.VK_S));
		MenuItem exit=new MenuItem("Exit");
		
		MenuItem view=new MenuItem("HelpView");
		MenuItem info=new MenuItem("Information");
		 
		//Menu.add(MenuItem m) : 메뉴에 메뉴아이템을 배치하는 메소드
		file.add(open);
		file.add(save);
		file.addSeparator();//메뉴아이템을 구분하는 구분선을 배치하는 메소드
		file.add(exit);
		
		help.add(view);
		help.add(info);

		//MenuBar.add(Menu m) : 메뉴바에 메뉴를 배치하는 메소드
		menuBar.add(file);
		menuBar.add(help);
		
		//Frame.setMenuBar(MenuBar menuBar) : 프레임의 메뉴바를 변경하는 메소드
		setMenuBar(menuBar);
		
		TextArea textArea=new TextArea();
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		add(textArea, BorderLayout.CENTER);
		
		setBounds(500, 100, 1000, 800);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MenuBarApp("MenuBar");
	}
}