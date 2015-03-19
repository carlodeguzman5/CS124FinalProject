
public abstract class Upgrade {
	protected int level, baseDamage, price;
	protected String name;
	public int getLevel(){
		return level;
	}	
	public int getBaseDamage(){
		return baseDamage;
	}
	public void levelUp(){
		level++;
	}
	public int getPrice(){
		return price; // CAN BE A VISITOR
	}
	public String getName(){
		return name;
	}
}
