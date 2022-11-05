package ar.edu.ungs.stylebus.modules.products.domain;

public enum Category {
	PREMIUM(70),
	STANDARD_PLUS(50),
	STANDARD(50);

	private final Integer passengersQuantity;

	Category(int passengersQuantity) {
		this.passengersQuantity = passengersQuantity;
	}

	public Integer passengersQuantity() {
		return passengersQuantity;
	}
}
