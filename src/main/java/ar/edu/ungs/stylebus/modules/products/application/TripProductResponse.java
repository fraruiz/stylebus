package ar.edu.ungs.stylebus.modules.products.application;

import ar.edu.ungs.stylebus.modules.products.domain.Trip;

import java.math.BigDecimal;

public final class TripProductResponse extends ProductResponse {
	private final LocationResponse origin;
	private final LocationResponse destiny;

	public TripProductResponse() {
		super();
		this.origin = null;
		this.destiny = null;
	}

	public TripProductResponse(String id, BigDecimal amount, LocationResponse origin, LocationResponse destiny) {
		super(id, amount, "trip");
		this.origin = origin;
		this.destiny = destiny;
	}

	public static TripProductResponse map(Trip trip) {
		return new TripProductResponse(trip.id(), trip.amount(), LocationResponse.map(trip.origin()), LocationResponse.map(trip.destiny()));
	}

	public LocationResponse origin() {
		return origin;
	}

	public LocationResponse destiny() {
		return destiny;
	}
}
