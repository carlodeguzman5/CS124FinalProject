
public class PriceVisitor implements Visitor {
	@Override
	public int visit(Upgrade u) {
		return u.getLevel() * u.getPrice() + 5;
	}

}
