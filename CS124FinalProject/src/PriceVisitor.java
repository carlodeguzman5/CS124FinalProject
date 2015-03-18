
public class PriceVisitor implements Visitor {

	@Override
	public int visit(Weapon w) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int visit(Upgrade u) {
		return u.getLevel() * u.getPrice();
	}

}
