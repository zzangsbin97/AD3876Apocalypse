package gameMain;

import Item.*;
import GamePanels.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class JPFrame extends JFrame 
{
	
	private JLabel backgroundMap;
	private Player player;
	
	public JPFrame() 
	{
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}
	
	
	private void initObject() 
	{
		// backgroundMap = new JLabel(new ImageIcon("Image/backgroundMapService.png"));
		backgroundMap = new JLabel(new ImageIcon("Image/bg_test1.png"));
		setContentPane(backgroundMap);
		
		this.player = new Player();
		this.add(player);
	}
	
	
	
	private void initSetting()
	{
		setSize(1000, 640);
		setLayout(null);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 다 GamePanel에 있음
	}
	
	
	
	private void initListener() 
	{
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

	public static void main(String[] args) 
	{
		new JPFrame();
		new ItemProperty();
	}

}