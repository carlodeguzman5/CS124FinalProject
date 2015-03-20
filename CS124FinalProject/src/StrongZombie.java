import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StrongZombie extends Zombie{
	private final int SPAWN_RADIUS = 250;
	private BufferedImage image;
	
	public StrongZombie() {	
		try {
			image = ImageIO.read(new File("img/Zombie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		health = 10; // Static Zombie Health
		damage = 25;// Static Zombie Damage
		bounty = 10; // Static Kill Bounty
		
		int deg = (int) (Math.random() * 360);
		
		xpos = (int) (Math.cos(Math.toRadians(deg)) * SPAWN_RADIUS) + 250;
		ypos = (int) (Math.sin(Math.toRadians(deg)) * SPAWN_RADIUS) + 250;
				
		runner = new Thread(this);
		runner.start();
	}
  
	@Override
	public void draw(Graphics g){
		g.drawImage(image, xpos-(getSize()+10)/2, ypos-(getSize()+10)/2, getSize()+10, getSize()+10, null);
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

	@Override
	public void update(int x) {
		if(x == 0){
			state = new SlowedState(this);
		}
		else if(x == 1){
			state = new FrozenState(this);
		}
		
	}
	
}
