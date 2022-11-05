package ar.edu.ungs.stylebus.modules.products.application;

import ar.edu.ungs.stylebus.modules.products.domain.Product;

import java.math.BigDecimal;

public abstract class ProductResponse {
	private final String id;
	private final BigDecimal amount;

	public ProductResponse(String id, BigDecimal amount) {
		this.id = id;
		this.amount = amount;
	}

	public static ProductResponse map(Product product) {
		return null;
	}

	public String id() {
		return id;
	}

	public BigDecimal amount() {
		return amount;
	}
}
