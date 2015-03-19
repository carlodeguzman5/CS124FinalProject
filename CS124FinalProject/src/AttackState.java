
public class AttackState implements State{
	private Zombie z;
	public AttackState(Zombie z){
		this.z = z;
	}
	@Override
	public void defaultAction() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		z.getEngine().character.damage(z.damage);
		//System.out.println("CONTACT");
	}

	@Override
	public void reachedPlayer() {
		// TODO Auto-generated method stub
		
	}

}
