import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ZombieFactory {
	private ArrayList<String> zombieList;
	private ZombieFlyWeight basis;
	private GameEngine engine;
	public ZombieFactory(GameEngine ge) throws FileNotFoundException{
		zombieList = new ArrayList<String>();
		basis = new ZombieFlyWeight(ge);
		File zList = new File("zombie.cfg");
		engine = ge;
		Scanner in = new Scanner(zList);
		while(in.hasNextLine()){
			zombieList.add(in.nextLine().trim());
			
		}	 
	}

	public Zombie createZombie(){
		int chance = (int)(Math.floor(Math.random() * zombieList.size()));
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class myClass;
		Zombie thisZombie = null;
		try {
			myClass = classLoader.loadClass(zombieList.get(chance));
			thisZombie = (Zombie) myClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(thisZombie.getClass().getName());
		thisZombie.setSource(basis); // Setting Flyweight Reference
		//Setting of Health and Bounty depending on stage
		thisZombie.setHealth(thisZombie.health * engine.stage);
		thisZombie.setBounty(engine.stage);
		return thisZombie;
	}
}
