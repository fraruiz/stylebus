package ar.edu.ungs.stylebus.modules.products.domain;

import java.math.BigDecimal;

public final class Trip extends Product {
	private final Location origin;
	private final Location destiny;
	private final Category category;
	private final Boolean goingBack;

	public Trip(String id,
	            BigDecimal amount,
	            Location origin,
	            Location destiny,
	            Category category,
	            Boolean goingBack) {
		super(id, amount);
		this.origin = origin;
		this.destiny = destiny;
		this.category = category;
		this.goingBack = goingBack;
	}

	public Location origin() {
		return origin;
	}

	public Location destiny() {
		return destiny;
	}

	public Category category() {
		return category;
	}

	public Boolean goingBack() {
		return goingBack;
	}
}
