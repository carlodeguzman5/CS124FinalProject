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
	public synchronized void attack(){
		//engine.bulletList.add(new Bullet(engine.character.direction, engine));
		engine.bulletPool.get(0).setDirection(engine.character.direction);
		engine.bulletPool.get(0).move();
		engine.bulletList.add(engine.bulletPool.remove(0));
		System.out.println("FIRE: "+engine.bulletPool.size());
		
		
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
	public void damage(int x){
		health -= x;
		engine.infoFrame.progressBar.setValue(health);
		if(health <= 0){
			health = 0;
			die();
		}
	}	
	private void die(){
		engine.killAllThreads();
	}
	
}
