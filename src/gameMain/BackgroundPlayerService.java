package gameMain;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;



public class BackgroundPlayerService implements Runnable
{
	
	private BufferedImage image;
	private Player player;
	
	
	
	
	public BackgroundPlayerService(Player player) 
	{
		this.player = player;
		
		try 
		{
			image = ImageIO.read(new File("Image/bg_test1.png"));
			// image = ImageIO.read(new File("image/backgroundMapService.png"));
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run()
	{
		while(true)
		{
			// Color Color1 = new Color(image.getRGB(player.getX() + 63, player.getY()));
			Color Color1 = new Color(image.getRGB(player.getX() + 53, player.getY())); // 우상
			Color Color2 = new Color(image.getRGB(player.getX() - 3 , player.getY())); // 좌상
			// Color Color3 = new Color(image.getRGB(player.getX(), player.getY() + 50)); // 좌하
			Color Color3 = new Color(image.getRGB(player.getX(), player.getY() + 51));
			// Color Color4 = new Color(image.getRGB(player.getX() + 63, player.getY() + 50)); // 우하
			Color Color4 = new Color(image.getRGB(player.getX() + 50, player.getY() + 50));
					
			if(player.checkRGB(Color2, 255, 0, 0))
			{
				player.setLeft(false);;
				player.setLeftWallCrash(true);
			}
			else if(player.checkRGB(Color1, 255,  0, 0))
			{
				player.setRight(false);;
				player.setRightWallCrash(true);
			}
			else	
			{
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			if((player.checkRGB(Color3, 255, 0, 0) || player.checkRGB(Color4, 255, 0, 0) || player.checkRGB(Color3, 0, 0, 255) || player.checkRGB(Color4, 0, 0, 255)))
			{
				player.setPlatform(true);
			}
			else if(!player.isUp() && !player.isDown() && !player.checkRGB(Color3, 255, 0, 0) && !player.checkRGB(Color4, 255, 0, 0) && !player.checkRGB(Color3, 0, 0, 255) && !player.checkRGB(Color4, 0, 0, 255))
			{
				player.setPlatform(false);
				player.down();
			}
			
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

}