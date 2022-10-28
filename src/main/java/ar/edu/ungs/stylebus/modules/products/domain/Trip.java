package ar.edu.ungs.stylebus.modules.products.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Trip extends Product {
	private final Location origin;
	private final Location destiny;

	public Trip(String id, BigDecimal amount, Location origin, Location destiny) {
		super(id, amount);
		this.origin = origin;
		this.destiny = destiny;
	}

	public Location origin() {
		return origin;
	}

	public Location destiny() {
		return destiny;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Trip trip = (Trip) o;
		return Objects.equals(origin, trip.origin) && Objects.equals(destiny, trip.destiny);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), origin, destiny);
	}

	@Override
	public String toString() {
		return "Trip{" + "origin=" + origin + ", destiny=" + destiny + '}';
	}
}
