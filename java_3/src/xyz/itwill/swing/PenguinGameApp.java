package xyz.itwill.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PenguinGameApp extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int JFRAME_WIDTH=646;
	private static final int JFRAME_HEIGHT=461;
	private static final int PENGUIN_SIZE=50;
	
	private Image backImage;
	private Image[] penguins;

	private int penguinNo;
	private int penguinX, penguinY;
	
	//펭귄 이미지의 이동 방향을 저장하기 위한 필드
	// => 1 : 왼쪽 방향, 2 : 오른쪽 방향
	private int direction;
	
	//게임 실행 상태를 저장하기 위한 필드
	// => false : 중지상태, true : 실행상태(true)
	private boolean isRun;
	
	//펭귄 상태를 저장하기 위한 필드
	// => false : 죽음상태, true : 생존상태(true)
	private boolean isPenguinAlive;
	
	//돌멩이 이미지의 폭과 높이를 저장하기 상수필드
	private static final int STONE_SIZE=30;
	
	//돌멩이 이미지를 저장하기 위한 필드
	private Image stoneImage;

	//다수의 돌멩이 정보(StoneThread 객체)를 저장하기 위한 필드 - 콜렉션
	private Set<StoneThread> stoneSet;
	
	public PenguinGameApp(String title) {
		super(title);
		
		backImage=new ImageIcon(getClass().getResource("/images/back.jpg")).getImage();  
		penguins=new Image[3];
		for(int i=0;i<penguins.length;i++) {
			penguins[i]=new ImageIcon(getClass().getResource("/images/penguin"+(i+1)+".gif")).getImage();
		}
		stoneImage=new ImageIcon(getClass().getResource("/images/stone.gif")).getImage();  
		
		stoneSet=new HashSet<StoneThread>();
		
		init();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode=e.getKeyCode();
				
				switch (keyCode) {
				case KeyEvent.VK_LEFT: 
					direction=1;
					break;
				case KeyEvent.VK_RIGHT: 
					direction=2;
					break;
				case KeyEvent.VK_P:
					//게임 실행 상태를 반대로 변경하여 저장 - 토글(Toggle)
					isRun=!isRun;
					if(!isRun) repaint();
					break;
				case KeyEvent.VK_F5:
					//게임 재실행을 위해 초기화 메소드 호출
					if(!isPenguinAlive) init();
					break;
				}
			}
		});
				
		setResizable(false);//프레임의 크기를 변경하지 않도록 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(700, 200, JFRAME_WIDTH, JFRAME_HEIGHT);
		setVisible(true);
	}
	
	//게임 실행 관련 초기화 작업을 처리하기 위한 메소드 - 필드 초기화
	private void init() {
		penguinNo=0;
		penguinX=JFRAME_WIDTH / 2 - PENGUIN_SIZE / 2;
		penguinY=JFRAME_HEIGHT - PENGUIN_SIZE;
		direction=0;
		isRun=true;
		isPenguinAlive=true;
		
		new PenguinAnimationThread().start();
		new CreateStoneThread().start();
	}
	
	public static void main(String[] args) {
		new PenguinGameApp("Penguin Game");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(backImage, 0, 0, JFRAME_WIDTH, JFRAME_HEIGHT, this);
		
		if(isPenguinAlive) {
			g.drawImage(penguins[penguinNo], penguinX, penguinY, PENGUIN_SIZE, PENGUIN_SIZE, this);
			
			if(!isRun) {
				g.setFont(new Font("굴림체", Font.BOLD, 50));
				g.setColor(Color.RED);
				g.drawString("일시 중지", 200, 200);
			}
		} else {
			g.setFont(new Font("굴림체", Font.BOLD, 50));
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 150, 200);
			g.drawString("다시(F5)", 200, 300);
		}
		
		//콜렉션 객체에 저장된 모든 돌멩이 정보를 이용하여 돌멩이 이미지 출력 처리
		synchronized (stoneSet) {
			for(StoneThread stone : stoneSet) {
				g.drawImage(stoneImage, stone.stoneX, stone.stoneY, STONE_SIZE, STONE_SIZE, this);
			}
		}
	}
	
	//펭귄 이미지를 움직이는 기능을 제공하는 클래스 
	// => 새로운 스레드를 생성하여 펭귄 이미지의 좌표값이 변경되도록 처리
	public class PenguinAnimationThread extends Thread {
		@Override
		public void run() {
			while(isPenguinAlive) {
				if(isRun) {
					switch(direction) {
					case 1:
						penguinX-=5;
						if(penguinX <= 0) {
							penguinX=0;
						}
						break;
					case 2:
						penguinX+=5;
						if(penguinX >= JFRAME_WIDTH-PENGUIN_SIZE) {
							penguinX=JFRAME_WIDTH-PENGUIN_SIZE;
						}
						break;	
					}
					
					penguinNo++;
					penguinNo%=3;
					repaint();
				}
				
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//돌멩이 정보를 저장하기 위한 클래스
	// => 돌멩이 이미지가 움직이도록 새로운 스레드를 사용해 돌멩이 이미지의 좌표값 변경
	public class StoneThread extends Thread {
		//돌멩이 이미지가 출력될 좌표값을 저장하기 위한 필드
		private int stoneX, stoneY;

		//돌멩이 상태를 저장하기 위한 필드
		// => false : 소멸상태, true : 존재상태(기본)
		private boolean isStoneAlive;
		
		//돌멩이 이미지가 떨어지는 속도를 저장하기 위한 필드
		private int stoneSpeed;
		
		public StoneThread() {
			stoneX=new Random().nextInt(JFRAME_WIDTH-STONE_SIZE);
			stoneY=0;
			isStoneAlive=true;
			stoneSpeed=10;
			
			//스레드 클래스로 객체를 생성하면 자동으로 새로운 스레드가 생성되어 run() 메소드를
			//호출하여 명령 실행
			start();
		}
		
		@Override
		public void run() {
			while(isPenguinAlive && isStoneAlive) {
				if(isRun) {
					stoneY+=5;
					
					if(stoneY >= JFRAME_HEIGHT-STONE_SIZE) {
						
					}
				
					//펭귄 이미지 출력 좌표값과 돌멩이 이미지 출력 좌표값이 중복될 경우 펭귄의
					//상태를 죽음상태로 변경 
					if(stoneY+20 >= penguinY) {//Y 좌표값 비교
						if(stoneX+10 >= penguinX && stoneX+10 <= penguinX+PENGUIN_SIZE
							&& stoneX+20 >= penguinX && stoneX+20 <= penguinX+PENGUIN_SIZE) {//X 좌표값 비교
							isPenguinAlive=false;
							synchronized (stoneSet) {
							    stoneSet.clear();	
							}
							repaint();
						}
					}
				}
				
				try {
					Thread.sleep(stoneSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//돌멩이 정보(StoneThread 객체)를 생성하여 콜렉션 객체의 요소값으로 저장하는 클래스
	// => 새로운 스레드로 StoneThread 객체를 생성하여 콜렉션 객체의 요소값으로 저장
	public class CreateStoneThread extends Thread {
		@Override
		public void run() {
			while(isPenguinAlive) {
				if(isRun) {
					synchronized (stoneSet) {
						stoneSet.add(new StoneThread());
					}
					
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}














