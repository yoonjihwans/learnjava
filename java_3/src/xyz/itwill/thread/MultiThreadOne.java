package xyz.itwill.thread;

//스레드를 생성하기 위해 선언된 클래스
public class MultiThreadOne extends Thread {
	
	
	@Override
	public void run() {
		for(char i='A';i<='Z';i++) {
			System.out.print(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}