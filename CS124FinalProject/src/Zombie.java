import java.awt.Graphics;


public abstract class Zombie implements Runnable{
	private ZombieFlyWeight source;
	private int size;
	protected int targetX, targetY, xpos, ypos;
	protected State state;
	
	public Zombie(){
		state = new DefaultState(this);
	}

	public void setSource(ZombieFlyWeight s){
		source = s;
		size = source.getSize();
		targetX = source.getTargetX();
		targetY = source.getTargetY();
		
	}
	public int getSize(){
		return size;  
	}
	
	public boolean checkHit(Bullet b){
		if(b.x2 < xpos+size && b.x2 > xpos && b.y2 > ypos && b.y2 < ypos+size){
			return true;
		}
		return false;
	}
	
	public abstract void draw(Graphics g);
}
