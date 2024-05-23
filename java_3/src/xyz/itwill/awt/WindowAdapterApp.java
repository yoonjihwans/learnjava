package xyz.itwill.awt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowAdapterApp extends Frame {
	private static final long serialVersionUID = 1L;

	public WindowAdapterApp(String title) {
		super(title);

		
		
		setBounds(800, 200, 300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WindowAdapterApp("dsd");
	}

	public class WindowEventHandle extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {

			super.windowClosing(e);
		}
	}

}
