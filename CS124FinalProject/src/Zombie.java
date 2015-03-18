import java.awt.Graphics;


public abstract class Zombie implements Runnable{
	private ZombieFlyWeight source;
	protected int xpos, ypos, health;
	protected State state;
	
	public Zombie(){
		state = new DefaultState(this);
	}

	public void setSource(ZombieFlyWeight s){
		source = s;
	}
	public int getSize(){
		return source.getSize();
	}
	
	public int getTargetX(){
		return source.getTargetX();
	}
	
	public int getTargetY(){
		return source.getTargetY();
	}
	
	public boolean checkHit(Bullet b){
		if(b.x2 < xpos+getSize() && b.x2 > xpos && b.y2 > ypos && b.y2 < ypos+getSize()){
			return true;
		}
		return false;
	}
	
	public boolean damage(int x){
		health-= x;
		if(health <= 0){
			return true;
		}
		return false;
	}
	
	public abstract void draw(Graphics g);
}
