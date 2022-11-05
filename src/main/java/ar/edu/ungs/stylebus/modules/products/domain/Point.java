package ar.edu.ungs.stylebus.modules.products.domain;

public final class Point {
	private final double longitude;
	private final double latitude;

	public Point(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double longitude() {
		return longitude;
	}

	public double latitude() {
		return latitude;
	}
}
