
public class DefaultState implements State{
	private Zombie z;
	private double rad;
	public DefaultState(Zombie z){
		this.z = z;
	}
	@Override
	public void defaultAction() {
		
		try {
			Thread.sleep(25);
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
}
