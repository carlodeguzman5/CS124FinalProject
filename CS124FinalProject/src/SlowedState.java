import java.util.Timer;
import java.util.TimerTask;

public class SlowedState implements State{
	Zombie z;
	public SlowedState(Zombie z){
		this.z = z;
	}
	@Override
	public void defaultAction() {
		Timer timer = new Timer();
		timer.schedule(new SlowTimer(), 5000);
		try {
			Thread.sleep(70);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(Math.pow(z.xpos-z.getTargetX(),2) + Math.pow(z.ypos-z.getTargetY(),2) <= 400 ){
			z.state = new AttackState(z);
		}
		else{
			if(z.getTargetX() < z.xpos){
				z.xpos--;
			}
			if(z.getTargetX() > z.xpos){
				z.xpos++;
			}
			if(z.getTargetY() < z.ypos){
				z.ypos--;
			}
			if(z.getTargetY() > z.ypos){
				z.ypos++;
			}
		}
	}
	
	class SlowTimer extends TimerTask{
		@Override
		public void run() {
			z.state = new DefaultState(z);
		}
		
	}
	
}