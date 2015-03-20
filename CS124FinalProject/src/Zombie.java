import java.awt.Graphics;


public abstract class Zombie implements Runnable, Observer{
	protected ZombieFlyWeight source;
	protected int xpos, ypos, health, damage, bounty, multiplier;
	protected State state;
	protected Thread runner; 
	
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
	
	public GameEngine getEngine(){
		return source.getEngine();
	}
	
	public void killThread(){
		runner.suspend();
	}
	public int getBounty(){
		return bounty;
	}
	
	public boolean checkHit(Bullet b){
		if(b.x < xpos+getSize() && b.x > xpos && b.y > ypos && b.y < ypos+getSize()){
			return true;
		}
		return false;
	}
	
	public void setHealth(int x){
		health = x;
	}
	public void setBounty(int x){
		bounty = x;
	}
	
	
	public boolean damage(int x){
		health-= x;
		if(health <= 0){
			return true;
		}
		return false;
	}
	public abstract void attack();
	public abstract void draw(Graphics g);
}
