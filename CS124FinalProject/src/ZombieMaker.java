

public class ZombieMaker implements Runnable{
	private GameEngine engine;
	private double prob;
	private int chance, totalZombies, zombieCount;
	public ZombieMaker(GameEngine engine){
		totalZombies = 15;
		this.engine = engine;
		prob = 99.0;	
	}
	@Override
	public void run() {
		while(true){
			while(zombieCount < totalZombies){
				chance = (int)(Math.random() * 100);
				if(chance > prob){
					System.out.println(zombieCount + " | " + totalZombies);
					engine.zombieList.add(engine.factory.createZombie());
					prob = 100.0;
					zombieCount++;
					
					//System.out.println("Created Enemy");
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				prob *= 0.9995;		
			}
			/*
			 * Stage End
			 */

			engine.stageHandler.setStage(engine.stage++);
			engine.infoFrame.setStage(engine.stage);
			
			
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
