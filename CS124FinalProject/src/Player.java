import java.util.ArrayList;


public class Player {
	private double direction;
	private GameEngine engine;
	private final int baseDamage = 1; //will depend on weapon
	private final int size = 50;
	private int health;
	private DamageVisitor visitor;
	
	
	
	ArrayList<Upgrade>upgrades;
	
	public Player(GameEngine e){
		engine = e;
		visitor = new DamageVisitor();
		upgrades = new ArrayList<Upgrade>();
		health = 100;
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
	public boolean damage(int x){
		health -= x;
		if(health <= 0){
			health = 0;
			return true;
		}
		return false;
	}
	
}
