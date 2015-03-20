import java.awt.Color;
import java.awt.Graphics;


public class Bullet implements Runnable{
	private double direction;
	private GameEngine engine;
	private final int SPEED = 2;
	private Thread t;
	
	public double x, y;
	public Bullet(double dir, GameEngine eg){
		direction = dir;
		engine = eg;
		x = engine.centerX;
		y = engine.centerY;
		t = new Thread(this);
		t.start();
		t.suspend();
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillArc((int)x-5,(int)y-5,10,10,0,360);
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			x += Math.cos(direction)*SPEED;
			y += Math.sin(direction)*SPEED;
		}
	}
	@SuppressWarnings("deprecation")
	public void killThread(){
		t.suspend();
	}
	public void setDirection(double dir){
		direction = dir;
	}
	public void move(){
		t.resume();
	}
	public void reset(){
		
		x = engine.centerX;
		y = engine.centerY;
		killThread();
		
	}
}
