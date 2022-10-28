package ar.edu.ungs.stylebus.modules.products.domain;

import java.awt.*;
import java.util.Objects;

public final class Location {
	private final Point coordinates;
	private final String city;

	public Location(Point coordinates, String city) {
		this.coordinates = coordinates;
		this.city = city;
	}

	public Point coordinates() {
		return coordinates;
	}

	public String city() {
		return city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Location location = (Location) o;
		return Objects.equals(coordinates, location.coordinates) && Objects.equals(city, location.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinates, city);
	}

	@Override
	public String toString() {
		return "Ubication{" + "coordinates=" + coordinates + ", city='" + city + '\'' + '}';
	}
}
