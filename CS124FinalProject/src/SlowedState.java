
public class SlowedState implements State{
	Zombie z;
	public SlowedState(Zombie z){
		this.z = z;
	}
	@Override
	public void defaultAction() {
		
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(Math.pow(z.xpos-z.getTargetX(),2) + Math.pow(z.ypos-z.getTargetY(),2) <= 10000 ){
			z.state = new AttackState(z);
		}
		else{
			if(z.getTargetX() < z.xpos){
				z.xpos--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(z.getTargetX() > z.xpos){
				z.xpos++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(z.getTargetY() < z.ypos){
				z.ypos--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(z.getTargetY() > z.ypos){
				z.ypos++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}