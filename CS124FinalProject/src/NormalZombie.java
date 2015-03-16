import java.awt.Color;
import java.awt.Graphics;

public class NormalZombie extends Zombie{
	private int size;
	private final int SPAWN_RADIUS = 300;
	public NormalZombie() {	
		xpos = (int) (Math.random() * 300);
		//ypos = (int) (Math.random() * 400);
		
		ypos = (int) (Math.pow(SPAWN_RADIUS,2) - Math.pow(xpos-250, 2));
		ypos = (int) Math.sqrt(ypos) - 100;
		
		// X^2 + Y^2 = R^2
		// R^2 - X^2  = Y^2
		
		//r^2 - (x-)
		
		Thread runner = new Thread(this);
		runner.start();
	}
  
	@Override
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillArc(xpos - size/2, ypos - size/2, size, size, 0, 360);
	}
	
	public void setSource(ZombieFlyWeight s){
		super.setSource(s);
		size = super.getSize();
		targetX = super.targetX;
		targetY = super.targetY;
	}

	@Override
	public void run() {
		while(true){
			state.defaultAction();
		}
	}
	
}
