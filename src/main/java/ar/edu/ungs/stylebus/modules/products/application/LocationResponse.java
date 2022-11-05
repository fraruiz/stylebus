package ar.edu.ungs.stylebus.modules.products.application;

import ar.edu.ungs.stylebus.modules.products.domain.Location;

public final class LocationResponse {
	private final String city;

	public LocationResponse() {
		this.city = null;
	}

	public LocationResponse(String city) {
		this.city = city;
	}

	public static LocationResponse map(Location location) {
		return new LocationResponse(location.city());
	}

	public String city() {
		return city;
	}
}
