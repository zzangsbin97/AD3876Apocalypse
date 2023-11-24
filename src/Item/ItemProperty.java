package Item;

import GamePanels.*;

import java.lang.System;
import java.awt.*;

import javax.management.timer.Timer;
import javax.swing.*;

import gameMain.BackgroundPlayerService;

import java.util.*;
import java.awt.event.*;



public class ItemProperty extends JFrame implements ActionListener{
	
	
	int xpos, ypos, hp;
	
	private ArrayList<ItemProperty> Items = new ArrayList<ItemProperty>();
	
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
	
	private void initItemCreate() {
		
		new Thread(new ItemCreate(this.Items)).start();
	}
	
	
	
	
}