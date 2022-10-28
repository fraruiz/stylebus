package ar.edu.ungs.stylebus.modules.products.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Package extends Product {
	private final Excursion excursion;
	private final Trip trip;

	public Package(String id, BigDecimal amount, Excursion excursion, Trip trip) {
		super(id, amount);
		this.excursion = excursion;
		this.trip = trip;
	}

	public Excursion excursion() {
		return excursion;
	}

	public Trip trip() {
		return trip;
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
		Package aPackage = (Package) o;
		return Objects.equals(excursion, aPackage.excursion) && Objects.equals(trip, aPackage.trip);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), excursion, trip);
	}

	@Override
	public String toString() {
		return "Package{" + "excursion=" + excursion + ", trip=" + trip + '}';
	}
}
