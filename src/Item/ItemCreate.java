package Item;

import GamePanels.*;

import java.lang.System;
import java.awt.*;
import java.awt.event.KeyListener;

import javax.management.timer.Timer;
import javax.swing.*;

import gameMain.BackgroundPlayerService;

import java.util.*;
import java.awt.event.*;





public class ItemCreate extends JLabel implements Runnable{
	
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


	public static void main(String[] args) {
		
//		new ItemProperty();

	}



	
}