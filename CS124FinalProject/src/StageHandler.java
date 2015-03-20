
public class StageHandler {
	GameEngine engine;
	public StageHandler(GameEngine ge){
		engine = ge;
	}
	
	public void setStage(int x){
		engine.totalZombieCount = x * 5 + 10;
		engine.zombieKilled = 0;
		
		engine.infoFrame.setStage(x);
		engine.infoFrame.setZombieCount(engine.totalZombieCount);
	}
}
