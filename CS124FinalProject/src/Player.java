import java.util.ArrayList;


public class Player {
	private double direction;
	private GameEngine engine;
	private int baseDamage = 1;
	private final int size = 50;
	private DamageVisitor visitor;
	
	ArrayList<Upgrade>upgrades;
	
	public Player(GameEngine e){
		engine = e;
		visitor = new DamageVisitor();
		upgrades = new ArrayList<Upgrade>();
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
	
	public int getDamage(){
		int temp = baseDamage;
		for(int i = 0; i < upgrades.size(); i++){
			temp += visitor.visit(upgrades.get(i));
		}
		return temp;
	}
	
}
