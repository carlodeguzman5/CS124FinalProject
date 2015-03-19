import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NormalZombie extends Zombie{
	private final int SPAWN_RADIUS = 250;
	private BufferedImage image;
	
	public NormalZombie() {	
		try {
			image = ImageIO.read(new File("img/Zombie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		health = 5; // Static Zombie Health
		damage = 10;// Static Zombie Damage
		bounty = 1; // Static Kill Bounty
		
		int deg = (int) (Math.random() * 360);
		
		xpos = (int) (Math.cos(Math.toRadians(deg)) * SPAWN_RADIUS) + 250;
		ypos = (int) (Math.sin(Math.toRadians(deg)) * SPAWN_RADIUS) + 250;
				
		runner = new Thread(this);
		runner.start();
	}
  
	@Override
	public void draw(Graphics g){
		g.drawImage(image, xpos-getSize()/2, ypos-getSize()/2, getSize(), getSize(), null);
	}
	
	@Override
	public void run() {
		while(true){
			state.defaultAction();
		}
	}

	@Override
	public void attack() {
		source.getEngine().character.damage(damage);
	}
	
}
