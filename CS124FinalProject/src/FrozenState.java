
public class FrozenState implements State {
	Zombie z;
	public FrozenState(Zombie z)
	{
		this.z = z;
	}
	
	@Override
	public void defaultAction() {
	
		try {
			Thread.sleep(10000);
			z.state = new DefaultState(z);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
