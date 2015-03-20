import java.util.ArrayList;


public class Player implements Subject{
	protected double direction;
	private GameEngine engine;
	private final int baseDamage = 1; //will depend on weapon
	private final int size = 50;
	private int health;
	private DamageVisitor visitor;
	
	private ArrayList<Observer> rangeList = new ArrayList<Observer>();
	
	
	ArrayList<Upgrade>upgrades;
	
	public Player(GameEngine e){
		engine = e;
		visitor = new DamageVisitor();
		upgrades = new ArrayList<Upgrade>();
		health = 100;
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

	@Override
	public void registerObserver(Observer o) {
		rangeList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		rangeList.remove(o);
	}

	@Override
	public void notifyObservers(int x) {
		for(Observer o :rangeList){
			Zombie z = (Zombie)o;
			z.update(x);
		}
	}
	
}
