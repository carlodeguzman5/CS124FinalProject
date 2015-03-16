
public class ZombieFlyWeight {
	GameEngine game;
	private int targetX, targetY;
	public ZombieFlyWeight(GameEngine ge){
		targetX = ge.canvasX/2 + ge.character.getSize()/2;
		targetY = ge.canvasY/2 + ge.character.getSize()/2;
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
