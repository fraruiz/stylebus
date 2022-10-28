package ar.edu.ungs.stylebus.modules.products.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Excursion extends Product {
	private final Location location;

	public Excursion(String id, BigDecimal amount, Location location) {
		super(id, amount);
		this.location = location;
	}

	public Location ubication() {
		return location;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Excursion excursion = (Excursion) o;
		return Objects.equals(location, excursion.location);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location);
	}

	@Override
	public String toString() {
		return "Excursion{" + "ubication=" + location + '}';
	}
}
