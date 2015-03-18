

public class ZombieMaker implements Runnable{
	private GameEngine ref;
	private double prob;
	private int chance, totalZombies, zombieCount;
	public ZombieMaker(GameEngine ref){
		totalZombies = 15;
		this.ref = ref;
		prob = 99.0;	
	}
	@Override
	public void run() {
		while(true){
			chance = (int)(Math.random() * 100);
			if(zombieCount >= totalZombies){
				//Do Nothing
			}
			else if(chance > prob){
				ref.zombieList.add(ref.factory.createZombie());
				prob = 100.0;
				zombieCount++;
				
				System.out.println("Created Enemy");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			prob *= 0.9995;		
		}
	}
	
	public void setTotalZombies(int x){
		totalZombies = x;
	}
	public boolean isDepleted(){
		if(zombieCount >= totalZombies){
			zombieCount = 0;
			return true;
		}
		return false;
	}
}
