
public class DamageVisitor implements Visitor{

	@Override
	public int visit(Weapon w) {
		return 0;
	}

	@Override
	public int visit(Upgrade u) {
		return 0;
	}
	
}
