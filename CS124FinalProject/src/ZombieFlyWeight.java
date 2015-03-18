
public class ZombieFlyWeight {
	GameEngine game;
	private int targetX, targetY;
	public ZombieFlyWeight(GameEngine ge){
		targetX = ge.centerX;
		targetY = ge.centerY;
	}
	private int size = 20;
	
	public int getSize(){
		return size;
	}
	public int getTargetX(){
		return targetX;
	}
	public int getTargetY(){
		return targetY;
	}
}
