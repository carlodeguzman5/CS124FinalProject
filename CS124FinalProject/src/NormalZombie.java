import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NormalZombie extends Zombie{
	private final int SPAWN_RADIUS = 250;
	private BufferedImage image;
	private double slope;
	public NormalZombie() {	
		try {
			image = ImageIO.read(new File("img/Zombie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int deg = (int) (Math.random() * 360);
		
		xpos = (int) (Math.cos(Math.toRadians(deg)) * SPAWN_RADIUS) + 250;
		ypos = (int) (Math.sin(Math.toRadians(deg)) * SPAWN_RADIUS) + 250;
				
		Thread runner = new Thread(this);
		runner.start();
	}
  
	@Override
	public void draw(Graphics g){
		g.drawImage(image, xpos-getSize()/2, ypos-getSize()/2, getSize(), getSize(), null);
		//g.setColor(Color.RED);
		//g.fillArc(xpos - size/2, ypos - size/2, size, size, 0, 360);
	}
	
	public void setSource(ZombieFlyWeight s){
		super.setSource(s);
	}

	@Override
	public void run() {
		while(true){
			state.defaultAction();
		}
	}
	
}
