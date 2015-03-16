
public class Player {
	double direction;
	GameEngine engine;
	private final int size = 20;
	public Player(GameEngine e){
		engine = e;
	}
	public void attack(){
		engine.bulletList.add(new Bullet(engine.character.direction, engine));
	}
	public void setDirection(double x){
		direction = x;
	}
	public int getSize(){
		return size;
	}
}
