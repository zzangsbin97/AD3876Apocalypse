package GamePanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Item.*;
import gameMain.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter



public class GamePanel extends JFrame{
	
	
	public JLabel backgroundMap;
	
	public GamePanel() {
		panelInitSetting();
	}
	
	public void panelInitSetting() {
		
		setLayout(null);
		setSize(1000, 640); // 프레임 크기 설정
		setLocationRelativeTo(null); // 중앙배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backgroundMap = new JLabel(new ImageIcon("Image/bg_test1.png"));
		setContentPane(backgroundMap);
		setVisible(true);
		
	}
	
	// 여기서부터 gameMain관련 
	public Player player;
	private void initObjectForJPFrame() {
		player = new Player();
		add(player);
	}
	
	
	public void initListenerForJPFrame() {
		addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e) 
			{
				switch(e.getKeyCode()) 
				{
					case KeyEvent.VK_LEFT:
						if(!player.isLeft() && !player.isLeftWallCrash())
						{
							player.left();
						}			
						break;
					case KeyEvent.VK_RIGHT:
						if(!player.isRight() && !player.isRightWallCrash()) 
						{
							player.right();
						}
						
						break;
					case KeyEvent.VK_UP:
						if(!player.isUp())
						{
							player.jump();
						}
						break;
				}
				System.out.println(e.getKeyCode());
			}
			
			public void keyReleased(KeyEvent e) 
			{
				switch(e.getKeyCode()) 
				{
					case KeyEvent.VK_LEFT:
						player.setLeft(false);		
						break;
					case KeyEvent.VK_RIGHT:
						player.setRight(false);
						break;
					/*case KeyEvent.VK_UP:
						player.down();
						break;*/
				}
			}
		});
	}
	
	
	// 여기서부터 ItemCreate
	class ItemCreate extends JPanel implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		private ArrayList<ItemProperty> Items = new ArrayList<ItemProperty>();
		
		public ItemCreate(ArrayList<ItemProperty> Items) {
			for (int i = 0 ; i < 8 ; i++ ) {// 아이템 갯수는 나중에 변동 가능함
				this.Items = Items;
				Items.add(new ItemProperty( (int)(Math.random()*1080), (int)(Math.random() * 720), 40) ); 
				// Item객체 넣기
				// 아이템 x위치 랜덤, y위치 랜덤, 체력(내구도)
			}
			
			addMouseListener(new MouseAdapter(){
				public void mousePressed( MouseEvent e ) {
					for ( int i = 0 ; i < Items.size() ; i++ ) {
						Rectangle rect = new Rectangle(Items.get(i).xpos, Items.get(i).ypos, 100, 100);
						
						if ( rect.contains(e.getPoint())) {
							Items.get(i).isExist = false;
							Items.remove(i);
							repaint();
						}
						
						/*
						if ( (e.getX() >= Items.get(i).xpos) && (e.getX() <= Items.get(i).xpos + 100) && (e.getY() >= Items.get(i).ypos) && (e.getY() <= Items.get(i).ypos + 100)) {
							Items.remove(i);
							repaint();
							
						} */
					}
				}
			});
		}
		
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for ( ItemProperty i : Items) {
				i.draw(g);
			}
		}
	}
	
	
	
	// 여기서부터 ItemProperty
	public int xpos, ypos, HP;
	class ItemProperty implements ActionListener{
		
		
		int xpos, ypos, hp;
		
		public ArrayList<ItemProperty> Items = new ArrayList<ItemProperty>();
		
		public void ItemPropertySetting() {
			add (new ItemCreate(Items));
		}
		
		public boolean isExist;
		
		int xspeed = 2;
		int yspeed = 2;
		
		Image itemBoxImage; // 이미지 변수 itemImg 선언
		Image itemImage_1;
		
		public ItemProperty( int xpos, int ypos, int hp){ // Item 초기화
			this.xpos = xpos; // 아이템 x좌표
			this.ypos = ypos; // 아이템 y좌표
			this.hp = hp; // 아이템 체력(내구도)
			this.isExist = true;
			
			ImageIcon itemBoxIcon = new ImageIcon("Image/box.png"); //아이템 이미지
			ImageIcon itemIcon_1 = new ImageIcon("Image/item1.png");
			
			itemBoxImage = itemBoxIcon.getImage(); // itemImg에 itemIcon 할당
			itemImage_1 = itemIcon_1.getImage();
		}
		
		public ItemProperty() {
			setSize(1000, 640);
			setTitle("아이템을 얻고 몬스터를 물리쳐 성장하세요!");
			add( new ItemCreate(this.Items));
			// Timer timer = new Timer(10, this); // 왜 에러뜨는지 모르겠음...
			// timer.start();
			// setDefaultCloseOperation(EXIT_ON_CLOSE); // x누르면 종료
			setVisible(true); // 창 보이게
		}
		
		public void draw(Graphics g) { // Item 그리기
			if (this.isExist = true) {
				g.drawImage(itemBoxImage, xpos, ypos, 100, 100, null);
			}
			else {
				g.drawImage(itemImage_1, xpos, ypos, 100, 100, null);
			}
			move(); // Item 이동
		}
		
		public void move() { // Item 이동
			// xpos += xspeed; // x축 이동
			// ypos += yspeed; // y축 이동
		}
		
		public void actionPerformed(ActionEvent e) {
			move();
			repaint();
		}
		
		private void initItemCreate() {
			
			new Thread(new ItemCreate(this.Items)).start();
		} // 여기까지 ItemProperty
		
		
	}
	

	
	
	public static void main(String[] args) {
		GamePanel gamepanel = new GamePanel();
		new Player();
		new BackgroundPlayerService(gamepanel.player);
		
	}
}