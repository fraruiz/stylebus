package ar.edu.ungs.stylebus.modules.carts.domain;

public final class CartNotExists extends RuntimeException {
	public CartNotExists(String id) {
		super(String.format("the cart <%s> not exists", id));
	}
}
