package ar.edu.ungs.stylebus.modules.products.domain;

public final class ProductNotExists extends RuntimeException {
	public ProductNotExists(String id) {
		super(String.format("the product <%s> not exists", id));
	}
}
