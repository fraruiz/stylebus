package ar.edu.ungs.stylebus.modules.products.application;

import ar.edu.ungs.stylebus.modules.products.domain.Excursion;

import java.math.BigDecimal;

public final class ExcursionProductResponse extends ProductResponse {
	private final LocationResponse location;

	public ExcursionProductResponse() {
		this.location = null;
	}

	public ExcursionProductResponse(String id, BigDecimal amount, LocationResponse location) {
		super(id, amount, "excursion");
		this.location = location;
	}

	public static ExcursionProductResponse map(Excursion excursion) {
		return new ExcursionProductResponse(excursion.id(), excursion.amount(), LocationResponse.map(excursion.ubication()));
	}

	public LocationResponse location() {
		return location;
	}
}
