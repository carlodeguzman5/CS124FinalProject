
public class StageHandler {
	int totalZombieCount;
	GameEngine engine;
	public StageHandler(GameEngine ge){
		engine = ge;
	}
	
	public void setStage(int x){
		totalZombieCount = x * 5 + 10;
		engine.zMaker.setTotalZombies(totalZombieCount);
		
		engine.infoFrame.setZombieCount(totalZombieCount);
	}
}
