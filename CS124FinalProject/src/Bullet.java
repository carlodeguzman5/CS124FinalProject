import java.awt.Color;
import java.awt.Graphics;


public class Bullet implements Runnable{
	private double direction;
	private GameEngine engine;
	private final int LENGTH = 10;
	private final int SPEED = 2;
	private Thread t;
	
	public double x, y, x2, y2;
	public Bullet(double dir, GameEngine eg){
		direction = dir;
		engine = eg;
		x = engine.centerX;
		y = engine.centerY;
		x2 = engine.centerX + (int)(Math.cos(direction)*LENGTH);
		y2 = engine.centerY + (int)(Math.sin(direction)*LENGTH);
		
		t = new Thread(this);
		//t.start();
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.drawLine((int)x, (int)y, (int)x2, (int)y2);
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
			
			x2 += Math.cos(direction)*SPEED;
			y2 += Math.sin(direction)*SPEED;
			
			if(x > engine.canvasX || x < 0 || y > engine.canvasY || y < 0){
				engine.bulletList.remove(this);
				engine.bulletPool.add(this);
				System.out.println("TEST:" + engine.bulletPool.size());
				reset();
			}
			
		}
	}
	@SuppressWarnings("deprecation")
	public void killThread(){
		t.stop();
	}
	public void setDirection(double dir){
		direction = dir;
	}
	public void move(){
		t.start();
	}
	public void reset(){
		x = y = x2 = y2 = 0;
		killThread();
		System.out.println("RESET BULLET");
	}
}
