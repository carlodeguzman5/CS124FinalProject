
public class DamageVisitor implements Visitor{
	@Override
	public int visit(Upgrade u) {
		return u.getLevel()*u.getBaseDamage();
	}
	
}
