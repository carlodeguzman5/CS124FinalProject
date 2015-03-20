public class FrenzyState implements State {
	Zombie z;

	FrenzyState(Zombie z) {
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
			
		}
		else{
			if(z.getTargetX() < z.xpos){
				z.xpos = z.xpos-2;
			}
			if(z.getTargetX() > z.xpos){
				z.xpos = z.xpos+2;
			}
			if(z.getTargetY() < z.ypos){
				z.ypos = z.ypos-2;
			}
			if(z.getTargetY() > z.ypos){
				z.ypos = z.ypos+2;
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//z.getEngine().character.damage((z.damage*2));
		}
	}
}
