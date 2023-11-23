package Item;

import java.lang.System;
import java.awt.*;
import java.awt.event.KeyListener;

import javax.management.timer.Timer;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;



class Item extends JFrame implements ActionListener{
	
	int xpos, ypos, hp;
	
	public boolean isExist;
	
	int xspeed = 2;
	int yspeed = 2;
	
	Image itemBoxImage; // 이미지 변수 itemImg 선언
	Image itemImage_1;
	
	public Item( int xpos, int ypos, int hp){ // Item 초기화
		this.xpos = xpos; // 아이템 x좌표
		this.ypos = ypos; // 아이템 y좌표
		this.hp = hp; // 아이템 체력(내구도)
		this.isExist = true;
		
		ImageIcon itemBoxIcon = new ImageIcon("C:\\image\\box.png"); //아이템 이미지
		ImageIcon itemIcon_1 = new ImageIcon("C:\\image\\item1.png");
		
		itemBoxImage = itemBoxIcon.getImage(); // itemImg에 itemIcon 할당
		itemImage_1 = itemIcon_1.getImage();
	}
	
	public Item() {
		setSize(1080, 720);
		setTitle("아이템을 얻고 몬스터를 물리쳐 성장하세요!");
		add( new ItemTest());
		// Timer timer = new Timer(10, this); // 왜 에러뜨는지 모르겠음...
		// timer.start();
		setDefaultCloseOperation(EXIT_ON_CLOSE); // x누르면 종료
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
}

public class ItemTest extends JPanel{
	
	ArrayList<Item> Items = new ArrayList<Item>();
	
	public ItemTest() {
		for (int i = 0 ; i < 8 ; i++ ) {// 아이템 갯수는 나중에 변동 가능함
			Items.add(new Item( (int)(Math.random()*1080), (int)(Math.random() * 720), 40) ); 
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
		for ( Item i : Items) {
			i.draw(g);
		}
	}


	public static void main(String[] args) {
		
		Item item = new Item();
					
		return;
	}
}